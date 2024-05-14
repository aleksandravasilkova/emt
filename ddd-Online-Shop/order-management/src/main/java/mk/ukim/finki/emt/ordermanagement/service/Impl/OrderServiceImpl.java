package mk.ukim.finki.emt.ordermanagement.service.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import mk.ukim.finki.emt.sharedkernel.domain.events.order.OrderItemCreated;
import mk.ukim.finki.emt.sharedkernel.infra.DomainEventPublisher;
import org.springframework.data.domain.DomainEvents;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.lang.reflect.Executable;
import java.time.Instant;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm,"order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size()>0) {
            throw new ConcurrentModificationException("The order form is not valid", (Throwable) constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItemList().forEach(item-> DomainEventPublisher.publish(new OrderItemCreated(item.getProductId().getId(),item.getQuantity())));

        return newOrder.id();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws RuntimeException  {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.addItem(orderItemForm.getProduct(),orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        DomainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(),orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws RuntimeException, RuntimeException {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(),orderForm.getCurrency());
        orderForm.getItems().forEach(item->order.addItem(item.getProduct(),item.getQuantity()));
        return order;
    }

}

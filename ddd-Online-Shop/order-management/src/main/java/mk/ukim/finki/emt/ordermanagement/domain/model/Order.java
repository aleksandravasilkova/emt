package mk.ukim.finki.emt.ordermanagement.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObject.Product;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.finfancial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.finfancial.Money;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    private Instant orderDate;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;




    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItemList = new HashSet<>();


    public Order(Instant now, java.util.@NonNull Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.orderDate = now;
       this.currency=currency;
    }

    public Order() {
        super(OrderId.randomId(OrderId.class));
    }




    public Money total() {
        return orderItemList.stream().map(OrderItem::subtotal).reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Product product, int qty) {
        Objects.requireNonNull(product,"product must not be null");
        var item  = new OrderItem(product.getId(),product.getPrice(),qty);
        orderItemList.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItemList.removeIf(v->v.getProductId().equals(orderItemId));
    }

    public OrderId id() {
        return id();
    }
}


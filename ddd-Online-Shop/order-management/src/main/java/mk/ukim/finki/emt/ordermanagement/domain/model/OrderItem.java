package mk.ukim.finki.emt.ordermanagement.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObject.ProductId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import mk.ukim.finki.emt.sharedkernel.domain.finfancial.Money;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(name = "quantity's",nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;




    public OrderItem(@NonNull ProductId productId, @NonNull Money itemPrice, int qty) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.itemPrice = itemPrice;
        this.quantity = qty;
    }

    public OrderItem() {
        super(DomainObjectId.randomId(OrderItemId.class));
    }


    public Money subtotal() {
        return itemPrice.multiply(quantity);
    }
}



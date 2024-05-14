package mk.ukim.finki.emt.ordermanagement.service.forms;

import mk.ukim.finki.emt.ordermanagement.domain.valueObject.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderItemForm {
    @NotNull
    private Product product;

    @Min(1)
    private int quantity = 1;

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }
}

package mk.ukim.finki.emt.productcatalog.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import mk.ukim.finki.emt.productcatalog.domain.valueObjcts.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.finfancial.Money;

@Entity
@Table(name="product")
@Getter
public class Product extends AbstractEntity<ProductId> {
private String productName;
private Quantity quantity;
private Money price;
private String description;


    private int sales = 0;


    public Product() {
        super(ProductId.randomId(ProductId.class));
    }
    public static Product build(String productName, Money price, int sales) {
        Product p = new Product();
        p.price = price;
        p.productName = productName;
        p.sales = sales;
        return p;
    }

    public void addSales(int qty) {
        this.sales = this.sales + qty;
    }

    public void removeSales(int qty) {
        this.sales = this.sales-qty;
    }



}

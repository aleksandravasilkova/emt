package mk.ukim.finki.emt.productcatalog.services.Form;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.finfancial.Money;
@Data
public class ProductForm {
    private String productName;
    private Money price;
    private int sales;

}

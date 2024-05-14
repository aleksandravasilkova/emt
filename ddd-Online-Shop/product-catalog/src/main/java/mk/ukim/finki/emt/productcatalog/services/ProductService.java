package mk.ukim.finki.emt.productcatalog.services;

import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.services.Form.ProductForm;

import java.util.List;

public interface ProductService {
    Product findById(ProductId id) throws Exception;
    Product createProduct(ProductForm form);
    Product orderItemCreated(ProductId productId, int quantity) throws Exception;
    Product orderItemRemoved(ProductId productId, int quantity) throws Exception;
    List<Product> getAll();

}

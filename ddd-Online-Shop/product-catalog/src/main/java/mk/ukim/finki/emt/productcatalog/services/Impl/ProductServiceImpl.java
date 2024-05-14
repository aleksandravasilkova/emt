package mk.ukim.finki.emt.productcatalog.services.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.productcatalog.domain.models.Product;
import mk.ukim.finki.emt.productcatalog.domain.models.ProductId;
import mk.ukim.finki.emt.productcatalog.domain.repository.productRepository;
import mk.ukim.finki.emt.productcatalog.services.Form.ProductForm;
import mk.ukim.finki.emt.productcatalog.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final mk.ukim.finki.emt.productcatalog.domain.repository.productRepository productRepository;

    @Override
    public Product findById(ProductId id) throws Exception {
        return productRepository.findById(id).orElseThrow(Exception::new);
    }

    @Override
    public Product createProduct(ProductForm form) {
        Product p = Product.build(form.getProductName(), form.getPrice(), form.getSales());
        productRepository.save(p);
        return p;
    }

    @Override
    public Product orderItemCreated(ProductId productId, int quantity) throws Exception {
        Product p = productRepository.findById(productId).orElseThrow(Exception::new);
        p.addSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public Product orderItemRemoved(ProductId productId, int quantity) throws Exception {
        Product p = productRepository.findById(productId).orElseThrow(Exception::new);
        p.removeSales(quantity);
        productRepository.saveAndFlush(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
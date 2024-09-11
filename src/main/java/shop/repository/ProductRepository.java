package shop.repository;

import shop.domain.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);
    List<Product> findAll();
    Product findById(long id);
    void update(Product product);
    void deleteById(long id);
}
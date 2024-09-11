package shop.repository;

import shop.domain.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer save(Customer product);
    List<Customer> findAll();
    Customer findById(long id);
    void update(Customer product);
    void deleteById(long id);
}
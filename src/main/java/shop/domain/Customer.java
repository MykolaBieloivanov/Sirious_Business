package shop.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Customer {

    private long id;
    private String name;
    private boolean isActive;
    private List<Product> products = new ArrayList<>();

    public Customer() {
    }

    public Customer(long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public void addProduct(Product product) {
        if (product.isActive()) {
            products.add(product);
        }
    }

    public List<Product> getAllActiveProducts() {
        return products
                .stream()
                .filter(Product::isActive)
                .toList();
    }

    public void deleteProductById(long id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product currentProduct = iterator.next();
            if (currentProduct.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public void clearProducts() {
        products.clear();
    }

    public double getActiveProductsTotalPrice() {
        return products
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double getActiveProductsAveragePrice() {
        return products
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && isActive == customer.isActive && Objects.equals(name, customer.name) && Objects.equals(products, customer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isActive, products);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Customer: id - ");
        builder
                .append(id)
                .append(", name - ")
                .append(name)
                .append(", active - ")
                .append(isActive ? "yes" : "no")
                .append("\n")
                .append("Products list:\n");

        for (Product product : products) {
            builder.append(product).append("\n");
        }

        return builder.toString();
    }
}
package shop.domain;

import java.util.Objects;

public class Product {

    private long id;
    private  String title;
    private double price;
    private boolean isActiv;


    public Product() {
    }


    public Product(long id, String title, double price, boolean isActiv) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.isActiv = isActiv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActiv() {
        return isActiv;
    }

    public void setActiv(boolean activ) {
        isActiv = activ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(price, product.price) == 0 && isActiv == product.isActiv && Objects.equals(title, product.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, isActiv);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title - %s, price - %.2f, activ - %s",
                id, title, price, isActiv ? "yes" : "no");
    }
}

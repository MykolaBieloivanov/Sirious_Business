package shop.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import shop.domain.Product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepositoryFile implements ProductRepository {

    private File database;
    private ObjectMapper mapper;
    private long currentId;

    public ProductRepositoryFile() {
        database = new File("product_db.txt");
        mapper = new ObjectMapper();

        try {
            database.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getMaxId();
    }

    private void getMaxId() {
        List<Product> products = findAll();
        if (!products.isEmpty()) {
            Product lastProduct = products.get(products.size() - 1);
            currentId = lastProduct.getId();
        }
        // 1, 3, 7
        // Добавляем продукты 1, 2, 3, 4, 5, 6, 7
        // Удаляем продукты 2, 4, 5, 6
        // Остаётся 1, 3, 7
    }

    @Override
    public Product save(Product product) {
        List<Product> products = findAll();
        product.setId(++currentId);
        product.setActive(true);
        products.add(product);

        try {
            mapper.writeValue(database, products);
            return product;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            Product[] products = mapper.readValue(database, Product[].class);
            List<Product> result = new ArrayList<>();
            Collections.addAll(result, products);
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Product findById(long id) {
        return null;
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void deleteById(long id) {

    }

//    public static void main(String[] args) {
//
//        File file = new File("test.txt");
//
//        try {
//            file.createNewFile();
//            ObjectMapper mapper1 = new ObjectMapper();
////            Product product = new Product(1, "Banana", 100, true);
////            mapper1.writeValue(file, product);
////
////            Product readProduct = mapper1.readValue(file, Product.class);
////            System.out.println(readProduct);
//
//            List<Product> products = List.of(
//                    new Product(1, "Banana", 100, true),
//                    new Product(2, "Apple", 90, true),
//                    new Product(3, "Orange", 180, true)
//            );
//
////            mapper1.writeValue(file, products);
//
//            // Один из способов чтения листа из файла (через массив)
//            Product[] readArray = mapper1.readValue(file, Product[].class);
//            List<Product> readList = new ArrayList<>();
//            Collections.addAll(readList, readArray);
//            System.out.println(readList);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
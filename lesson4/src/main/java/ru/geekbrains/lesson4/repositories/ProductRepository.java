package ru.geekbrains.lesson4.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.lesson4.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    @Value("5")
    private int startSize;
    private List<Product> productList;

    @PostConstruct
    private void init(){
        productList = new ArrayList<>();
        Long lastId = 1L;
        for (int i = 0; i < startSize; i++) {
            String name = "Product " + (int)((Math.random()*99)+1);
            double cost = (Math.random()*999)+1;
            productList.add(new Product(lastId,name,cost));
            lastId++;
        }
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public void deleteById(long id){
        productList.removeIf(product -> product.getId() == id);
    }

    public void add (Product product){
        productList.add(product);
    }
}

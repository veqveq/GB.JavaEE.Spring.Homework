package beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    @Value("10")
    private int listSize;
    private List<Product> productList;

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProduct(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        if (id != -1){
            System.out.println(String.format("Id = %d not found in repository", id));
        }
        return null;
    }

    @PostConstruct
    private void generate() {
        productList = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < listSize; i++) {
            id++;
            int titleNum = (int) (Math.random() * 99) + 1;
            double coast = Math.random() * 1000;
            productList.add(new Product(id, "product " + titleNum, coast));
        }
    }
}

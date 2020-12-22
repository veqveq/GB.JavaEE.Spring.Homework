package root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Repository {
    @Value("5")
    private int startSize;
    private List<Product> productList;

    @PostConstruct
    private void init(){
        productList=new ArrayList<>();
        int lastId = 1;
        for (int i = 0; i < startSize; i++) {
            String name = "Product " + (int)((Math.random()*99)+1);
            float cost = (float) Math.random()*999 +1;
            productList.add(new Product(lastId,name,cost));
            lastId++;
        }
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public void addProduct(int id, String title, float cost){
        productList.add(new Product(id,title,cost));
    }

    public void removeProduct(Integer id){
        for(Product product:productList){
            if (product.getId() == id){
                productList.remove(product);
                return;
            }
        }
    }
}

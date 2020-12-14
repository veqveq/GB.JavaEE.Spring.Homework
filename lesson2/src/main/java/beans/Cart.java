package beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> cartList = new ArrayList<>();
    private ProductRepository productRepository;

    public Cart(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getCartList() {
        return Collections.unmodifiableList(cartList);
    }

    public void add(int id) {
        Product product = productRepository.getProduct(id);
        if (product != null) {
            cartList.add(product);
            System.out.println(String.format("Product %d added in cart",id));
        }
    }

    public void del(int id) {
        for (Product product : cartList) {
            if (id == product.getId()) {
                cartList.remove(product);
                return;
            }
        }
        if (id != -1) System.out.println((String.format("Id = %d not found in cart", id)));
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}

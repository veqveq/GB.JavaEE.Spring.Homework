package beans;

import models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDao extends BasicDao<Product> {

    public ProductDao(ShopSessionsFactory factory) {
        super(factory);
    }

    @Override
    public void init() {
        cls = Product.class;
    }
}

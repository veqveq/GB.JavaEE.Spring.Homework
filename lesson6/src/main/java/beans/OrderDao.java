package beans;

import models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends BasicDao<Order> {

    public OrderDao(ShopSessionsFactory factory) {
        super(factory);
    }

    @Override
    public void init() {
        cls = Order.class;
    }
}

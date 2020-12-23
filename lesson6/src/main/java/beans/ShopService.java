package beans;

import models.Client;
import models.Order;
import models.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopService {
    private ClientDao clients;
    private ProductDao products;
    private OrderDao orders;
    private ShopSessionsFactory factory;

    public ShopService(ClientDao clients, ProductDao products, OrderDao orders, ShopSessionsFactory factory) {
        this.clients = clients;
        this.products = products;
        this.factory = factory;
        this.orders = orders;
    }

    public List<Product> findProductsFromClient(long clientId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Client client = clients.findById(clientId, session);
            List<Product> products = new ArrayList<>(client.getProducts());
            session.getTransaction().commit();
            return products;
        }
    }

    public List<Client> findBuyersOfProduct(long productId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = products.findById(productId, session);
            List<Client> clients = new ArrayList<>(product.getClients());
            session.getTransaction().commit();
            return clients;
        }
    }

    public Order findDetailsFromOrder(long orderId) {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            return orders.findById(orderId,session);
        }
    }

    public Order findDetailsFromOrder(long clientId, long productId) {
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            List<Order> allOrders = orders.findAll(session);
            for (Order o : allOrders) {
                if (o.getClient().getId() == clientId && o.getProduct().getId() == productId) {
                    return o;
                }
            }
            throw new RuntimeException("Order not found");
        }
    }

    public void close() {
        factory.close();
    }
}

package beans;

import models.Client;
import models.Order;
import models.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class OrderDao {
    private ShopSessionsFactory factory;
    private ClientDao clients;
    private ProductDao products;

    public OrderDao(ShopSessionsFactory factory, ClientDao clients, ProductDao products) {
        this.factory = factory;
        this.clients = clients;
        this.products = products;
    }

    public Order findById(long id, Session session) {
        Order order = session.get(Order.class, id);
        if (order == null) {
            throw new RuntimeException("Client not found");
        } else {
            return order;
        }
    }

    public List<Order> findAll(Session session) {
        List<Order> orders = session.createQuery(("FROM Order"), Order.class).getResultList();
        if (orders == null) {
            throw new RuntimeException("Orders not found");
        } else {
            return orders;
        }
    }

    public void deleteById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Order order = findById(id,session);
            session.delete(order);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            throw new RuntimeException("Client not found");
        }
    }

    public void save(long productId, long clientId) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = products.findById(productId, session);
            Client client = clients.findById(clientId, session);
            Order order = new Order(client, product, product.getCost());
            session.save(order);
            session.getTransaction().commit();
        }
    }

    public void save(long productId, long clientId, int cost) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = products.findById(productId, session);
            Client client = clients.findById(clientId, session);
            Order order = new Order(client, product, cost);
            session.save(order);
            session.getTransaction().commit();
        }
    }

    public void update(long orderId, Order order){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.update(String.valueOf(orderId),order);
            session.save(order);
            session.getTransaction().commit();
        }
    }
}

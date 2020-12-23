package beans;

import models.Client;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class ClientDao {
    private ShopSessionsFactory factory;

    public ClientDao(ShopSessionsFactory factory) {
        this.factory = factory;
    }

    public Client findById(long id, Session session) {
        Client client = session.get(Client.class, id);
        if (client == null) {
            throw new RuntimeException("Client not found");
        } else {
            return client;
        }
    }

    public List<Client> findAll(Session session) {
        List<Client> clients = session.createQuery(("FROM Client"), Client.class).getResultList();
        if (clients == null) {
            throw new RuntimeException("Clients not found");
        } else {
            return clients;
        }
    }

    public void deleteById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.delete(client);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            throw new RuntimeException("Client not found");
        }
    }

    public void saveOrUpdate(Client client) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(client);
            session.getTransaction().commit();
        }
    }
}

package beans;

import org.hibernate.Session;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import java.util.List;

public abstract class BasicDao<T> {
    protected Class cls;

    private ShopSessionsFactory factory;

    public BasicDao(ShopSessionsFactory factory) {
        this.factory = factory;
    }

    @PostConstruct
    public abstract void init();

    public T findById(long id, Session session) {
        T item = (T) session.get(cls, id);
        if (item == null) {
            throw new RuntimeException("Product not found");
        } else {
            return item;
        }
    }

    public List<T> findAll(Session session) {
        List<T> items = session.createQuery(String.format("FROM %s",cls.getSimpleName()), cls).getResultList();
        if (items == null) {
            throw new RuntimeException("Products not found");
        } else {
            return items;
        }
    }

    public void deleteById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            T item = (T) session.get(cls, id);
            session.delete(item);
            session.getTransaction().commit();
        } catch (NoResultException e) {
            throw new RuntimeException("Client not found");
        }
    }

    public void saveOrUpdate(T item) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
        }
    }
}

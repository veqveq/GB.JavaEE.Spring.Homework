import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.List;

public class ProductDao {

    private static SessionFactory factory;

    public static void main(String[] args) {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        try {
            System.out.println(findById(1));
            deleteById(1);
            System.out.println(findAll());
            saveOrUpdate(new Product("New Product",250));
            System.out.println(findAll());
            saveOrUpdate(new Product(6L,"Updated Product",120));
            System.out.println(findAll());
        } finally {
            factory.close();
        }
    }

    public static Product findById(long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery(String.format("SELECT s FROM Product s WHERE s.id=%d", id), Product.class).getSingleResult();
            session.getTransaction().commit();
            return product;
        }catch (NoResultException e){
            return null;
        }
    }

    public static List<Product> findAll(){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery(("from Product"), Product.class).getResultList();
            session.getTransaction().commit();
            return products;
        }catch (NoResultException e){
            return null;
        }
    }

    public static void deleteById(long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.createQuery(String.format("SELECT s FROM Product s WHERE s.id=%d", id), Product.class).getSingleResult();
            session.delete(product);
            session.getTransaction().commit();
        }catch (NoResultException e){
            System.out.println("Удаление не выполнено");;
        }
    }

    public static void saveOrUpdate(Product product){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }
}

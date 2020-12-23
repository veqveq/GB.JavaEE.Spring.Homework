package beans;

import models.Product;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;
import java.util.List;

@Component
public class ProductDao {
    private ShopSessionsFactory factory;

    public ProductDao(ShopSessionsFactory factory) {
        this.factory = factory;
    }

    public Product findById(long id, Session session){
        Product product = session.get(Product.class,id);
        if (product == null){
            throw new RuntimeException("Product not found");
        }else{
            return product;
        }
    }

    public List<Product> findAll(Session session) {
        List<Product> products = session.createQuery(("FROM Product"),Product.class).getResultList();
        if (products == null) {
            throw new RuntimeException("Products not found");
        } else {
            return products;
        }
    }

    public void deleteById(long id){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class,id);
            session.delete(product);
            session.getTransaction().commit();
        }catch (NoResultException e){
            throw new RuntimeException("Client not found");
        }
    }

    public void saveOrUpdate(Product product){
        try(Session session = factory.getCurrentSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }
}

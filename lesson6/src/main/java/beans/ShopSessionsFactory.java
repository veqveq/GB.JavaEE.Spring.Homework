package beans;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ShopSessionsFactory {
    private SessionFactory factory;

    @PostConstruct
    public void init() {
        factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Session getCurrentSession() {
        return factory.getCurrentSession();
    }

    public void close() {
        if (factory.getCurrentSession() != null) {
            factory.getCurrentSession().close();
        }
        factory.close();
    }
}

package beans;

import models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDao extends BasicDao<Client> {

    public ClientDao(ShopSessionsFactory factory) {
        super(factory);
    }

    @Override
    public void init() {
        cls = Client.class;
    }
}

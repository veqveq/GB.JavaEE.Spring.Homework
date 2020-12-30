package models;

import javax.persistence.*;

@Entity
@Table(name = "orders_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_id_fld")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id_fld")
    private Product product;

    @Column (name = "product_cost_fld")
    private int cost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client.getName() +
                ", product=" + product.getTitle() +
                ", cost=" + cost +
                '}';
    }

    public Order(Client client, Product product, int cost) {
        this.client = client;
        this.product = product;
        this.cost = cost;
    }

    public Order() {
    }
}

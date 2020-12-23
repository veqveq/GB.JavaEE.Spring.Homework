package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "cost_fld")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "orders_tbl",
            joinColumns = @JoinColumn(name = "product_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "client_id_fld")
    )
    private List<Client> clients;

    @OneToMany(mappedBy = "product")
    private List <Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}

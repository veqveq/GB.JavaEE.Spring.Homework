package models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients_tbl")
public class Client{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private long id;
    @Column(name = "name_fld")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "orders_tbl",
            joinColumns = @JoinColumn(name = "client_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "product_id_fld")
    )
    private List <Product> products;

    @OneToMany(mappedBy = "client")
    private List <Order> orders;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

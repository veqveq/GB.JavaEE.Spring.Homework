import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products_tbl")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private long id;
    @Column(name = "title_fld")
    private String title;
    @Column(name = "cost_fld")
    private int cost;

    public Product() {
    }

    public Product(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public Product(long id, String title, int cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
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

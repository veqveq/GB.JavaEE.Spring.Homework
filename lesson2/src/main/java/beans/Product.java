package beans;

public class Product {
    private int id;
    private String title;
    private double coast;

    public Product(int id, String title, double coast) {
        this.id = id;
        this.title = title;
        this.coast = coast;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Product: [id = %d | title = %s | coast = %.02f] \n",id,title,coast);
    }
}

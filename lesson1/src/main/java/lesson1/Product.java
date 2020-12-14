package lesson1;

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

    public String getTitle() {
        return title;
    }

    public double getCoast() {
        return coast;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", coast=" + coast +
                '}';
    }
}

package ru.geekbrains.lesson4.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
public class Product {
    private long id;
    private String title;
    private BigDecimal cost;

    public Product(long id, String title, Number cost) {
        this.id = id;
        this.title = title;
        this.cost = new BigDecimal(String.valueOf(cost)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(Number cost) {
        this.cost = new BigDecimal(String.valueOf(cost)).setScale(2, RoundingMode.HALF_UP);
    }
}


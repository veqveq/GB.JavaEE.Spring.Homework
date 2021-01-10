package com.veqveq.lesson8.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_fld")
    private Long id;
    @Column(name = "title_fld")
    private String title;
    @Column(name="cost_fld")
    private int cost;
}

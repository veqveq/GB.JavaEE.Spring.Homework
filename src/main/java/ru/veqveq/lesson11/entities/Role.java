package ru.veqveq.lesson11.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles_tbl")
public class Role {

    @Id
    @Column(name = "id_fld")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_fld")
    private String name;


}

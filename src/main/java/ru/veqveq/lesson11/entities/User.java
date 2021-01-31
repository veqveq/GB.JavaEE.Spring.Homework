package ru.veqveq.lesson11.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "users_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fld")
    private Long id;

    @Column(name = "username_fld")
    private String username;

    @Column(name = "password_fld")
    private String password;

    @OneToOne
    @JoinColumn(name = "id_fld")
    private Score scores;

    @ManyToMany
    @JoinTable(name = "users_roles_tbl",
            joinColumns = @JoinColumn(name = "user_id_fld"),
            inverseJoinColumns = @JoinColumn(name = "role_id_fld")
    )
    private Collection<Role> roles;
}

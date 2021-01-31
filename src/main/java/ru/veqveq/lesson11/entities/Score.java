package ru.veqveq.lesson11.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scores_tbl")
public class Score {

    @Id
    @Column(name = "user_id_fld")
    private Long user_id;

    @Column(name = "score_fld")
    private Long score;

    @OneToOne (mappedBy = "scores")
    private User user;
}

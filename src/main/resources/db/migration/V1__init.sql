create table users_tbl
(
    id_fld       bigint auto_increment,
    username_fld varchar(30) not null,
    password_fld varchar(80) not null,
    primary key (id_fld)
);

create table roles_tbl
(
    id_fld   bigint auto_increment,
    name_fld varchar(50) not null,
    primary key (id_fld)
);

CREATE TABLE users_roles_tbl
(
    user_id_fld bigint not null,
    role_id_fld int    not null,
    primary key (user_id_fld, role_id_fld),
    foreign key (user_id_fld) references users_tbl (id_fld),
    foreign key (role_id_fld) references roles_tbl (id_fld)
);

create table scores_tbl
(
    user_id_fld bigint,
    score_fld   bigint,
    primary key (user_id_fld),
    foreign key (user_id_fld) references users_tbl (id_fld)
);

insert into roles_tbl (name_fld)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users_tbl (username_fld, password_fld)
values ('user1', '$2y$12$ywvZtJlzE/CLnwnHRLJlseJ05AAkeE3UMyP2qGti1AbL5hwWFnhpW'),  //100
       ('user2', '$2y$12$ywvZtJlzE/CLnwnHRLJlseJ05AAkeE3UMyP2qGti1AbL5hwWFnhpW '); //100

insert into users_roles_tbl (user_id_fld, role_id_fld)
values (1, 1),
       (1, 2),
       (2, 1);

insert into scores_tbl (user_id_fld, score_fld)
values (1, 50),
       (2, 60);
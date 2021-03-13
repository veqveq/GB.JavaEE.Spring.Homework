create table products_tbl
(
    id_fld    bigint       not null auto_increment,
    title_fld varchar(255) not null,
    primary key (id_fld)
);

insert into products_tbl (title_fld)
values ('Product 1'),
       ('Product 2'),
       ('Product 3'),
       ('Product 4'),
       ('Product 5');

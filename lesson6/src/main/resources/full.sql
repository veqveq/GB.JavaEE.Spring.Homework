//Инициализация таблицы с продуктами
CREATE TABLE `products_bd`.`products_tbl` (
  `id_fld` BIGINT NOT NULL AUTO_INCREMENT,
  `title_fld` VARCHAR(255) NOT NULL,
  `cost_fld` INT NOT NULL,
  PRIMARY KEY (`id_fld`));
  INSERT INTO `products_bd`.`products_tbl` (`title_fld`, `cost_fld`) VALUES ('Product 1', '100');
  INSERT INTO `products_bd`.`products_tbl` (`title_fld`, `cost_fld`) VALUES ('Product 2', '120');
  INSERT INTO `products_bd`.`products_tbl` (`title_fld`, `cost_fld`) VALUES ('Product 3', '80');
  INSERT INTO `products_bd`.`products_tbl` (`title_fld`, `cost_fld`) VALUES ('Product 4', '50');
  INSERT INTO `products_bd`.`products_tbl` (`title_fld`, `cost_fld`) VALUES ('Product 5', '95');

//Инициализация таблицы с клиентами
CREATE TABLE `products_bd`.`clients_tbl` (
  `id_fld` BIGINT NOT NULL AUTO_INCREMENT,
  `name_fld` VARCHAR(255) NOT NULL DEFAULT 'Unknown',
  PRIMARY KEY (`id_fld`));
  INSERT INTO `products_bd`.`clients_tbl` (`name_fld`) VALUES ('Antony');
  INSERT INTO `products_bd`.`clients_tbl` (`name_fld`) VALUES ('Jacky');
  INSERT INTO `products_bd`.`clients_tbl` (`name_fld`) VALUES ('Jonhy');

//Инициализация таблицы с заказами
CREATE TABLE `products_bd`.`orders_tbl` (
  `id_fld` BIGINT NOT NULL AUTO_INCREMENT,
  `client_id_fld` BIGINT NOT NULL,
  `product_id_fld` BIGINT NOT NULL,
  `product_cost_fld` INT NULL,
  PRIMARY KEY (`id_fld`),
  INDEX `fk_client_idx` (`client_id_fld` ASC) VISIBLE,
  INDEX `fk_product_idx` (`product_id_fld` ASC) VISIBLE,
  CONSTRAINT `fk_client`
    FOREIGN KEY (`client_id_fld`)
    REFERENCES `products_bd`.`clients_tbl` (`id_fld`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product`
    FOREIGN KEY (`product_id_fld`)
    REFERENCES `products_bd`.`products_tbl` (`id_fld`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('1', '2', '50');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('1', '3', '20');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('2', '2', '65');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('1', '5', '100');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('2', '1', '25');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('3', '5', '150');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('3', '5', '55');
    INSERT INTO `products_bd`.`orders_tbl` (`client_id_fld`, `product_id_fld`, `product_cost_fld`) VALUES ('1', '4', '80');
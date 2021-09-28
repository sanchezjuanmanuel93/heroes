--liquibase formatted sql

--changeset juanmanuel:1
--Database: h2
CREATE TABLE heroes (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  description VARCHAR(255) NULL,
  PRIMARY KEY (id)
  );

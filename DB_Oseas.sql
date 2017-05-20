SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `db_oseas` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

GRANT ALL PRIVILEGES ON db_oseas.* TO oseas@"localhost" IDENTIFIED BY 'senha' WITH GRANT OPTION;

USE `db_oseas` ;

-- -----------------------------------------------------
-- Table `db_oseas`.`vendedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`vendedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `percentual` DOUBLE NOT NULL,
  `valor_a_receber` DOUBLE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_oseas`.`circulacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`circulacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_hora` DATETIME NULL,
  `vendedor_id` INT NOT NULL,
  `valor_total` DOUBLE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_circulacao_vendedor_idx` (`vendedor_id` ASC),
  CONSTRAINT `fk_circulacao_vendedor`
    FOREIGN KEY (`vendedor_id`)
    REFERENCES `db_oseas`.`vendedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_oseas`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `preco_final` DOUBLE NULL,
  `preco_atacado` DOUBLE NULL,
  `descricao` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_oseas`.`produto_circulando`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`produto_circulando` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qtd_circulando` INT NULL,
  `circulacao_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  PRIMARY KEY (`id`, `produto_id`),
  INDEX `fk_produto_circulando_circulacao1_idx` (`circulacao_id` ASC),
  INDEX `fk_produto_circulando_produto1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_produto_circulando_circulacao1`
    FOREIGN KEY (`circulacao_id`)
    REFERENCES `db_oseas`.`circulacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_circulando_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_oseas`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_oseas`.`estoque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`estoque` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qtd_estoque` INT NULL,
  `qtd_circulando` INT NULL,
  `produto_id` INT NOT NULL,
  PRIMARY KEY (`id`, `produto_id`),
  INDEX `fk_estoque_produto1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_estoque_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_oseas`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_oseas`.`produto_vendido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_oseas`.`produto_vendido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qtd_produto` INT NULL,
  `produto_id` INT NOT NULL,
  `circulacao_id` INT NOT NULL,
  `data_venda` DATETIME NULL,
  PRIMARY KEY (`id`, `produto_id`),
  INDEX `fk_produto_vendido_produto1_idx` (`produto_id` ASC),
  INDEX `fk_produto_vendido_circulacao1_idx` (`circulacao_id` ASC),
  CONSTRAINT `fk_produto_vendido_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `db_oseas`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_vendido_circulacao1`
    FOREIGN KEY (`circulacao_id`)
    REFERENCES `db_oseas`.`circulacao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

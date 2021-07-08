use CSCI5308_7_DEVINT;
delimiter |
CREATE PROCEDURE SP_CREATE_INSTITUTE_TABLES()
BEGIN
DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`institute_basic`;
CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`institute_basic` (
  `institute_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `regd_code` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `institute_state` VARCHAR(45) NOT NULL,
  `institute_contact` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  `pincode` VARCHAR(45) NOT NULL,
  `isblacklisted` ENUM("yes", "no") NOT NULL,
  PRIMARY KEY (`institute_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB3;

DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`institute_finance`;

 CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`institute_finance` (
  `institute_id` INT NOT NULL,
  `bank_acc_num` VARCHAR(45) NOT NULL,
  `Bank_IFSC` VARCHAR(45) NOT NULL,
  `bank_name` VARCHAR(45) NOT NULL,
  `bank_acc_holder_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`institute_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = UTF8MB3;
END 
|
CALL SP_CREATE_INSTITUTE_TABLES();
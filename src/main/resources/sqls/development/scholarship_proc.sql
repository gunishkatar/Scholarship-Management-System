DROP procedure IF EXISTS `SP_CREATE_SCHOLARSHIP_TABLES`;

DELIMITER |
CREATE PROCEDURE SP_CREATE_SCHOLARSHIP_TABLES()
BEGIN
DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`scholarship`;
CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`scholarship` (
    `scholarship_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `effectiveDate` date NOT NULL,
    `scholarship_amount` double NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    `criteria_girl` boolean NOT NULL,
    `criteria_academic` boolean NOT NULL,
    `criteria_sports` boolean NOT NULL,
    PRIMARY KEY (`scholarship_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = UTF8MB3;
END
|

CALL SP_CREATE_SCHOLARSHIP_TABLES();
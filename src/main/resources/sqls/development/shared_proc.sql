use
    CSCI5308_7_DEVINT;

DROP PROCEDURE IF EXISTS SP_CREATE_SHARED_TABLES;

delimiter
|
CREATE PROCEDURE SP_CREATE_SHARED_TABLES()
BEGIN

    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`user_credential`;
    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`user_credential`
    (
        `user_id`               VARCHAR(255)                  NOT NULL,
        `password`              VARCHAR(45)                   NOT NULL,
        `is_hard_blocked`       ENUM ('yes','no')             NOT NULL DEFAULT 'no',
        `is_soft_blocked`       ENUM ('yes','no')             NOT NULL DEFAULT 'no',
        `last_login_time`       DATETIME                      NOT NULL DEFAULT NOW(),
        `security_id`           VARCHAR(500)                  NOT NULL,
        `security_answer_one`   VARCHAR(500)                  NOT NULL,
        `security_answer_two`   VARCHAR(500)                  NOT NULL,
        `security_answer_three` VARCHAR(500)                  NOT NULL,
        `failed_login_count`    INT                           NOT NULL DEFAULT 0,
        `role_type`             ENUM ('student', 'institute') NOT NULL,
        `is_blackListed`        ENUM ('yes', 'no')            NOT NULL DEFAULT 'no',
        PRIMARY KEY (`user_id`)
    ) ENGINE = InnoDB
      DEFAULT CHARACTER SET = utf8mb3;

    DROP TABLE IF EXISTS `CSCI5308_7_DEVINT`.`feedback`;

    CREATE TABLE IF NOT EXISTS `CSCI5308_7_DEVINT`.`feedback`
    (
        `user_id`           VARCHAR(255) NOT NULL,
        `fb_question_one`   VARCHAR(255) NOT NULL,
        `fb_question_two`   VARCHAR(255) NOT NULL,
        `fb_question_three` VARCHAR(255) NOT NULL,
        `rating`            INT          NOT NULL,
        PRIMARY KEY (`user_id`)
    ) ENGINE = InnoDB
      DEFAULT CHARACTER SET = utf8mb3;
END
|

CALL SP_CREATE_SHARED_TABLES();

SHOW
    PROCEDURE STATUS;
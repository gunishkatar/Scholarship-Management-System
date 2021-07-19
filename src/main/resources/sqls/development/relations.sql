use CSCI5308_7_DEVINT;
DROP PROCEDURE IF EXISTS SP_CREATE_RELATIONS;

delimiter |
CREATE PROCEDURE SP_CREATE_RELATIONS()
BEGIN

    ALTER TABLE student_basic
        ADD CONSTRAINT FK_student_basic_user
            FOREIGN KEY (email_id) REFERENCES user_credential (user_id);

    ALTER TABLE student_academic
        ADD CONSTRAINT FK_academic_basic
            FOREIGN KEY (email_id) REFERENCES student_basic (email_id);

    ALTER TABLE student_non_academic
        ADD CONSTRAINT FK_non_academic_basic
            FOREIGN KEY (email_id) REFERENCES student_basic (email_id);

    ALTER TABLE student_finance
        ADD CONSTRAINT FK_finance_basic
            FOREIGN KEY (email_id) REFERENCES student_basic (email_id);

    ALTER TABLE application
        ADD CONSTRAINT FK_application_student_basic
            FOREIGN KEY (student_id) REFERENCES student_basic (email_id);

END
|

CALL SP_CREATE_RELATIONS();
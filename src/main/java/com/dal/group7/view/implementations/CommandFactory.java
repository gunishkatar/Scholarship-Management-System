package com.dal.group7.view.implementations;

import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.DaoFactory;
import com.dal.group7.persistent.implementations.PwdEncryptDao;
import com.dal.group7.service.implementation.*;
import com.dal.group7.shared.PwdEncrypt;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.persistent.implementations.DaoFactory.*;


public enum CommandFactory {
    CREATE_SCHOLARSHIP {
        @Override
        public Command getCommand() {
            return new CreateScholarshipCommand(new MinistryScholarshipService(SCHOLARSHIP.createDao(),
                    new JsonFileReader()));
        }
    },
    ERROR {
        @Override
        public Command getCommand() {
            return new ErrorCommand();
        }
    },
    GUEST {
        @Override
        public Command getCommand() {
            return new GuestCommand();
        }
    },
    HOME {
        @Override
        public Command getCommand() {
            return new HomeCommand();
        }
    },
    MINISTRY_HOME {
        @Override
        public Command getCommand() {
            return new MinistryHomeCommand();
        }
    },
    MINISTRY_LOGIN {
        @Override
        public Command getCommand() {
            return new MinistryLoginCommand(new MinistryLoginService(DaoFactory.USER_CREDENTIALS.createDao(),
                    new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()))));
        }
    },
    QUIT {
        @Override
        public Command getCommand() {
            return new QuitCommand();
        }
    },
    SELECT_LOGIN_USER {
        @Override
        public Command getCommand() {
            return new SelectLoginUserCommand();
        }
    },
    SIGN_UP {
        @Override
        public Command getCommand() {
            return new SignUpCommand();
        }
    },
    STUDENT_SIGNUP {
        @Override
        public Command getCommand() {
            return new StudentSignupCommand(
                    new StudentService(STUDENT.createDao(),
                            new JsonFileReader()));
        }
    },
    STUDENT_HOME {
        @Override
        public Command getCommand() {
            return new StudentHomeCommand();
        }
    },
    STUDENT_LOGIN {
        @Override
        public Command getCommand() {
            return new StudentLoginCommand(new StudentLoginService(DaoFactory.USER_CREDENTIALS.createDao(),
                    new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()))));
        }

    },
    INSTITUTE_SIGNUP {
        @Override
        public Command getCommand() {
            return new InstituteSignupCommand(
                    new InstituteService(INSTITUTE.createDao(),
                            new JsonFileReader()));
        }
    },
    INSTITUTE_HOME {
        @Override
        public Command getCommand() {
            return new InstituteHomeCommand();
        }
    },
    INSTITUTE_LOGIN {
        @Override
        public Command getCommand() {
            return new InstituteLoginCommand(new InstituteLoginService(DaoFactory.USER_CREDENTIALS.createDao(),
                    new PwdEncrypt(new PwdEncryptDao(new ConnectionManager()))));
        }
    },
    APPLY_FOR_SCHEME {
        @Override
        public Command getCommand() {
            return new ApplySchemeCommand(new StudentApplySchemeService(
                    DaoFactory.USER_CREDENTIALS.createDao(),
                    DaoFactory.APPLICATION.createDao(),
                    new JsonFileReader()));
        }
    },
    ;

    public abstract Command getCommand();
}

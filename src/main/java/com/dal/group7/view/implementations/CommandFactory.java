package com.dal.group7.view.implementations;

import com.dal.group7.service.implementation.*;
import com.dal.group7.shared.PwdEncrypt;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.persistent.implementations.DaoFactory.*;


public enum CommandFactory {
    CREATE_SCHOLARSHIP {
        @Override
        public Command getCommand() {
            return new CreateScholarshipCommand(ServiceConstants.MINISTRY_SCHOLARSHIP_SERVICE);
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
            return new MinistryLoginCommand(ServiceConstants.MINISTRY_LOGIN_SERVICE);
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
            return new StudentSignupCommand(ServiceConstants.STUDENT_SERVICE);
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
            return new StudentLoginCommand(ServiceConstants.STUDENT_LOGIN_SERVICE);
        }

    },
    INSTITUTE_SIGNUP {
        @Override
        public Command getCommand() {
            return new InstituteSignupCommand(ServiceConstants.INSTITUTE_SERVICE);
        }
    },
    INSTITUTE_HOME {
        @Override
        public Command getCommand() {
            return new InstituteHomeCommand();
        }
    },
    APPLY_FOR_SCHEME {
        @Override
        public Command getCommand() {
            return new ApplySchemeCommand(
                    ServiceConstants.APPLY_SCHEME_SERVICE);
        }
    },
    LIST_SCHOLARSHIPS {
        @Override
        public Command getCommand() {
            return new ListScholarshipCommand(
                    ServiceConstants.MINISTRY_SCHOLARSHIP_SERVICE);
        }
    },
    INSTITUTE_LOGIN {
        @Override
        public Command getCommand() {
            return new InstituteLoginCommand(ServiceConstants.LOGIN_SERVICE);
        }
    },
    INSTITUTE_APPROVE_REJECT {
        @Override
        public Command getCommand() {
            return new InstituteDecisionCommand(
                    ServiceConstants.INSTITUTE_APPLICATION_SERVICE);
        }
    };


    public abstract Command getCommand();

    private static class ServiceConstants {
        private static final MinistryScholarshipService MINISTRY_SCHOLARSHIP_SERVICE = new MinistryScholarshipService(
                SCHOLARSHIP.createDao(), new JsonFileReader());
        private static final MinistryLoginService MINISTRY_LOGIN_SERVICE = new MinistryLoginService(
                USER_CREDENTIALS.createDao(), new PwdEncrypt(ENCRYPTION.createDao()));
        private static final StudentService STUDENT_SERVICE = new StudentService(STUDENT.createDao(),
                new JsonFileReader());
        private static final StudentLoginService STUDENT_LOGIN_SERVICE = new StudentLoginService(
                USER_CREDENTIALS.createDao(), new PwdEncrypt(ENCRYPTION.createDao()));
        private static final InstituteService INSTITUTE_SERVICE = new InstituteService(INSTITUTE.createDao(),
                new JsonFileReader());
        private static final StudentApplySchemeService APPLY_SCHEME_SERVICE = new StudentApplySchemeService(
                USER_CREDENTIALS.createDao(), APPLICATION.createDao(), new JsonFileReader());
        private static final InstituteLoginService LOGIN_SERVICE = new InstituteLoginService(
                USER_CREDENTIALS.createDao(), new PwdEncrypt(ENCRYPTION.createDao()));
        private static final InstituteApplicationService INSTITUTE_APPLICATION_SERVICE =
                new InstituteApplicationService(APPLICATION.createDao());

    }
}

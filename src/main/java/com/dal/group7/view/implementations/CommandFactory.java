package com.dal.group7.view.implementations;

import com.dal.group7.persistent.implementations.ConnectionManager;
import com.dal.group7.persistent.implementations.DaoFactory;
import com.dal.group7.persistent.implementations.PwdEncryptDao;
import com.dal.group7.service.implementation.JsonFileReader;
import com.dal.group7.service.implementation.MinistryLoginService;
import com.dal.group7.service.implementation.MinistryScholarshipService;
import com.dal.group7.shared.PwdEncrypt;
import com.dal.group7.view.interfaces.Command;

import static com.dal.group7.persistent.implementations.DaoFactory.SCHOLARSHIP;

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
    };

    public abstract Command getCommand();
}

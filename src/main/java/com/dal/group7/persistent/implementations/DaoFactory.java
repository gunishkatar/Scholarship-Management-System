package com.dal.group7.persistent.implementations;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.Student;
import com.dal.group7.persistent.model.UserCredential;

public enum DaoFactory {

    INSTITUTE {
        @Override
        public Dao<Integer, Institute> createDao() {
            return new InstituteDao(connectionManager);
        }
    },
    ENCRYPTION {
        @Override
        public Dao createDao() {
            return new PwdEncryptDao(connectionManager);
        }
    },
    SCHOLARSHIP {
        @Override
        public Dao<Integer, Scholarship> createDao() {
            return new ScholarshipDao(connectionManager);
        }
    },
    STUDENT {
        @Override
        public Dao<Integer, Student> createDao() {
            return new StudentDao(connectionManager);
        }
    },

    USER_CREDENTIALS {
        @Override
        public Dao<String, UserCredential> createDao() {
            return new UserCredentialDao(connectionManager);
        }
    };

    private static final ConnectionManager connectionManager = new ConnectionManager();

    public abstract Dao createDao();
}

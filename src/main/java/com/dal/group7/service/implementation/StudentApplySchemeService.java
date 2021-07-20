package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import com.dal.group7.persistent.model.UserCredential;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class StudentApplySchemeService {
    private final Dao<String, UserCredential> userCredentialDao;
    private final Dao<String, Application> applicationDao;
    private final JsonFileReader jsonFileReader;
    private static final String NO = "no";


    public StudentApplySchemeService(
            Dao<String, UserCredential> userCredentialDao,
            Dao<String, Application> schemeApplicationDao,
            JsonFileReader jsonFileReader) {
        this.userCredentialDao = userCredentialDao;
        this.applicationDao = schemeApplicationDao;
        this.jsonFileReader = jsonFileReader;
    }

    public void applyScheme(String filepath) throws IOException, SQLException {
        final JSONObject jsonObject = jsonFileReader.readJson(filepath);
        Scheme scheme = new Scheme().from(jsonObject);
        UserCredential user = getUserDetails(scheme.getUserId());

        if (Boolean.TRUE.equals(isUserEligible(user)) &&
                Boolean.FALSE.equals(hasAppliedBefore(scheme.getUserId()))) {

            Application application = new Application();
            application.setScheme(scheme);
            generateScores(application);
            applicationDao.insertOne(application);

        } else {
            throw new IllegalArgumentException(
                    "User is not eligible for scholarship");
        }
    }

    private void generateScores(Application application) {

        switch (application.getScheme().getSchemeId()) {
            case 1:
                // TODO: Profile score
                break;
            case 2:
                // TODO: profile score
                break;
            default:
                application.setAcademicScore(0);
                application.setNonAcademicScore(0);
                application.setProfileScore(0);
        }

        // remove the code after writing actual logic.
        application.setAcademicScore(0);
        application.setNonAcademicScore(0);
        application.setProfileScore(0);
    }

    private boolean hasAppliedBefore(String userId) throws SQLException {
        return applicationDao.doesExist(userId);
    }

    private Boolean isUserEligible(UserCredential user) {

        return user.getIsHardBlock().equalsIgnoreCase(NO) &&
                user.getIsSoftBlock().equalsIgnoreCase(NO) &&
                user.getIsBlackListed().equalsIgnoreCase(NO);
    }

    private UserCredential getUserDetails(String userId)
            throws SQLException {

        return userCredentialDao.get(userId)
                .orElseThrow(
                        () -> new IllegalArgumentException("No User Found"));

    }
}

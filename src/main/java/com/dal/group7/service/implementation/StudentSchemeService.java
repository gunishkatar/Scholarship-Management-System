package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import com.dal.group7.persistent.model.UserCredential;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.dal.group7.constants.ViewConstants.NOT_ELIGIBLE;
import static com.dal.group7.constants.ViewConstants.NO_USER_FOUND;

public class StudentSchemeService {
    private final Dao<String, UserCredential> userCredentialDao;
    private final Dao<String, Application> applicationDao;
    private final JsonFileReader jsonFileReader;
    private static final String NO = "no";


    public StudentSchemeService(
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

        if (isApplicationValid(scheme) &&
                Boolean.TRUE.equals(isUserEligible(user)) &&
                Boolean.FALSE.equals(hasAppliedBefore(scheme.getUserId()))) {

            Application application = new Application();
            application.setScheme(scheme);
            generateScores(application);
            applicationDao.insertOne(application);

        } else {
            throw new IllegalArgumentException(NOT_ELIGIBLE);
        }
    }

    public boolean isApplicationValid(Scheme scheme) {
        Boolean emailID =
                !scheme.getUserId().equals("") && scheme.getUserId() != null;
        Boolean gender =
                !scheme.getGender().equals("") && scheme.getGender() != null;
        Boolean boards =
                !scheme.getBoardX().equals("") && scheme.getBoardX() != null &&
                        !scheme.getBoardXII().equals("") &&
                        scheme.getBoardXII() != null;
        Boolean bankDetails = scheme.getBankAccNumber() != null &&
                !scheme.getBankAccNumber().equals("") &&
                !scheme.getBankIFSC().equals("") &&
                scheme.getBankIFSC() != null &&
                !scheme.getBankName().equals("") &&
                scheme.getBankName() != null &&
                scheme.getBankHolderName() != null &&
                !scheme.getBankHolderName().equals("");

        return emailID && gender && boards && bankDetails;
    }

    public void generateScores(Application application) {

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

    public boolean hasAppliedBefore(String userId) throws SQLException {
        return applicationDao.doesExist(userId);
    }

    public Boolean isUserEligible(UserCredential user) {

        return user.getIsHardBlock().equalsIgnoreCase(NO) &&
                user.getIsSoftBlock().equalsIgnoreCase(NO) &&
                user.getIsBlackListed().equalsIgnoreCase(NO);
    }

    public UserCredential getUserDetails(String userId)
            throws SQLException {

        return userCredentialDao.get(userId)
                .orElseThrow(
                        () -> new IllegalArgumentException(NO_USER_FOUND));

    }

    public List<Application> viewStatus(String userId) throws SQLException {
        return applicationDao.getAllByUser(userId);
    }
}

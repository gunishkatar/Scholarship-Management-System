package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.constants.ApplicationConstants;
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
                calculateAcademicScore(application);
                break;
            case 2:
                application.setNonAcademicScore(calculateSportsScholarshipScore(application));
                break;
            case 3:
                //
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

    // Bussiness Logic for Calculating Academic Profile Score
    public double calculateAcademicScore(Application application){

        double XScore = ((application.getScheme().getGpaX() * 10) * 0.25);
        double XIIScore = ((application.getScheme().getGpaXII() * 10) * 0.35);
        double bachelorsScore =(( application.getScheme().getGpaBachelors() * 10) * 0.40);

        //double totalScoreWithoutBacklog = XScore + XIIScore + bachelorsScore;

        double XScoreAfterBacklog = XScore-(application.getScheme().getBacklogX() * (0.04 * XScore));
        double XIIScoreAfterBacklog = XIIScore-(application.getScheme().getBacklogXII() * (0.06 * XIIScore));
        double bachelorsScoreAfterBacklog = bachelorsScore-(application.getScheme().getBacklogBachelors() * (0.08 * bachelorsScore));

        double totalAcademicProfileScore = XScoreAfterBacklog + XIIScoreAfterBacklog + bachelorsScoreAfterBacklog;
        System.out.println("Total Profile Score " + totalAcademicProfileScore);
        return totalAcademicProfileScore;
    }
    // Bussiness Logic for Calculating Non-Academic Sports Scholarship Profile Score
    public double calculateSportsScholarshipScore(Application application){
        int numberofNationalSportsAward = application.getScheme().getNationalSportsAwards();
        int numberofStateSportsAward = application.getScheme().getStateSportsAwards();
        int numberofDistrictSportsAward = application.getScheme().getDistrictSportsAwards();
        double nationalSportsAwardPoints = 0;
        double stateSportsAwardPoints = 0;
        double districtSportsAwardPoints = 0;
        double totalSportsAwardPoints = 0;
        double sportScore = 0;

        if(numberofNationalSportsAward>ApplicationConstants.AWARD_CAP){
            nationalSportsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            nationalSportsAwardPoints = numberofNationalSportsAward * ApplicationConstants.POINT_FACTOR;
        }

        if(numberofStateSportsAward>ApplicationConstants.AWARD_CAP){
            stateSportsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            stateSportsAwardPoints = numberofStateSportsAward * ApplicationConstants.POINT_FACTOR;
        }

        if(numberofDistrictSportsAward>ApplicationConstants.AWARD_CAP){
            districtSportsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            districtSportsAwardPoints = numberofDistrictSportsAward * ApplicationConstants.POINT_FACTOR;
        }

        totalSportsAwardPoints = nationalSportsAwardPoints + stateSportsAwardPoints + districtSportsAwardPoints;

        sportScore = totalSportsAwardPoints * ApplicationConstants.RANGE_FACTOR;

        return sportScore;
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

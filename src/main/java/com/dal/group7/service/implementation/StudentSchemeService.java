package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scheme;
import com.dal.group7.persistent.model.UserCredential;
import com.dal.group7.constants.ApplicationConstants;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
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
                calculateSportsScholarshipScore(application);
                break;
            case 3:
                calculateArtsScholarshipScore(application);
                break;
            default:
                application.setAcademicScore(0);
                application.setNonAcademicScore(0);
                application.setProfileScore(0);
        }
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
        DecimalFormat df = new DecimalFormat();
        int numberofNationalSportsAward = application.getScheme().getNationalSportsAwards();
        int numberofStateSportsAward = application.getScheme().getStateSportsAwards();
        int numberofDistrictSportsAward = application.getScheme().getDistrictSportsAwards();
        float nationalSportsAwardPoints = 0.00f;
        float stateSportsAwardPoints = 0.00f;
        float districtSportsAwardPoints = 0.00f;
        float totalSportsAwardPoints = 0.00f;
        float sportScore = 0.00f;

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

        return Double.valueOf(df.format(sportScore));
    }

    // Bussiness Logic for Calculating Non-Academic Arts Scholarship Profile Score
    public double calculateArtsScholarshipScore(Application application){
        DecimalFormat df = new DecimalFormat();
        int numberofNationalArtsAward = application.getScheme().getNationalArtsAwards();
        int numberofStateArtsAward = application.getScheme().getStateArtsAwards();
        int numberofDistrictArtssAward = application.getScheme().getDistrictArtsAwards();
        float nationalArtsAwardPoints = 0.00f;
        float stateArtsAwardPoints = 0.00f;
        float districtArtsAwardPoints = 0.00f;
        float totalArtsAwardPoints = 0.00f;
        float artScore = 0.00f;

        if(numberofNationalArtsAward>ApplicationConstants.AWARD_CAP){
            nationalArtsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            nationalArtsAwardPoints = numberofNationalArtsAward * ApplicationConstants.POINT_FACTOR;
        }

        if(numberofStateArtsAward>ApplicationConstants.AWARD_CAP){
            stateArtsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            stateArtsAwardPoints = numberofStateArtsAward * ApplicationConstants.POINT_FACTOR;
        }

        if(numberofDistrictArtssAward>ApplicationConstants.AWARD_CAP){
            districtArtsAwardPoints = ApplicationConstants.POINT_CAP;
        }else{
            districtArtsAwardPoints = numberofDistrictArtssAward * ApplicationConstants.POINT_FACTOR;
        }

        totalArtsAwardPoints = nationalArtsAwardPoints + stateArtsAwardPoints + districtArtsAwardPoints;

        artScore = totalArtsAwardPoints * ApplicationConstants.RANGE_FACTOR;

        return Double.valueOf(df.format(artScore));
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

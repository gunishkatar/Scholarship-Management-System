package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Institute;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.StudentFinance;

import java.sql.SQLException;

import static com.dal.group7.persistent.model.ApplicationStatus.FUND_ISSUED;
import static com.dal.group7.persistent.model.ApplicationStatus.REJECTED;

public class MinistryApplicationService {
    private static final String MINISTRY_STATUS = "ministry_status";
    private static final String GRANT_AMOUNT = "grant_amount";
    private final Dao<String, Application> applicationDao;
    private final Dao<Integer, Scholarship> scholarshipDao;
    private final Dao<String, StudentFinance> studentFinanceDao;
    private final Dao<Integer, Institute> instituteDao;

    public MinistryApplicationService(Dao<String, Application> applicationDao,
                                      Dao<Integer, Scholarship> scholarshipDao,
                                      Dao<String, StudentFinance> studentFinanceDao,
                                      Dao<Integer, Institute> instituteDao) {
        this.applicationDao = applicationDao;
        this.scholarshipDao = scholarshipDao;
        this.studentFinanceDao = studentFinanceDao;
        this.instituteDao = instituteDao;
    }

    public Application getApplicationWithAmount(String applicationId) throws SQLException {
        final Application application =
                applicationDao.findById(applicationId).get();
        final int scholarshipId = application.getSchemeId();
        final Scholarship scholarship = scholarshipDao.findById(scholarshipId)
                .orElseThrow(IllegalArgumentException::new);
        final StudentFinance studentFinance =
                studentFinanceDao.findById(application.getStudentId())
                        .orElseThrow(IllegalArgumentException::new);
        final AllocationStrategy allocationStrategy =
                AllocationStrategy.from(studentFinance.getAnnualIncome());
        application.setTuitionAmount(
                allocationStrategy.getAllocatedTuitionAmount(scholarship));
        application.setInsuranceAmount(
                allocationStrategy.getAllocatedInsuranceAmount(scholarship));
        application.setTravelAmount(
                allocationStrategy.getAllocatedTravelAmount(scholarship));
        application.setLivingExpensesAmount(allocationStrategy
                .getAllocatedLivingExpenseAmount(scholarship));
        return application;
    }

    public boolean doesExist(String applicationNumber) throws SQLException {
        return applicationDao.doesExist(applicationNumber);
    }

    public void issueFundToApplication(Application application)
            throws SQLException {
        applicationDao
                .updateValue(application.getApplicationId(), MINISTRY_STATUS,
                        FUND_ISSUED.toString());
        applicationDao.setValues(application);
    }

    public void rejectApplication(String applicationNumber)
            throws SQLException {
        applicationDao.updateValue(applicationNumber, MINISTRY_STATUS,
                REJECTED.toString());
    }

    public void issueTenPercentGrantToInstitute(Application application)
            throws SQLException {
        
        double grantAmount = calculateTenPercentGrantAmount(application);

        instituteDao
                .updateValue(application.getInstituteId(), GRANT_AMOUNT,
                        grantAmount);
    }

    public double calculateTenPercentGrantAmount(
            Application fundedApplication) {
        double issuedTuitionAmount = fundedApplication.getTuitionAmount();
        double issuedTravelAmount = fundedApplication.getTravelAmount();
        double issuedInsuranceAmount = fundedApplication.getInsuranceAmount();

        return 0.1 * (issuedInsuranceAmount + issuedTravelAmount +
                issuedTuitionAmount);
    }
}

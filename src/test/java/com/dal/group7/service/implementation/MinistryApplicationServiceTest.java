package com.dal.group7.service.implementation;

import com.dal.group7.persistent.interfaces.Dao;
import com.dal.group7.persistent.model.Application;
import com.dal.group7.persistent.model.Scholarship;
import com.dal.group7.persistent.model.StudentFinance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static com.dal.group7.persistent.model.ApplicationStatus.FUND_ISSUED;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MinistryApplicationServiceTest {

    private static final String ID = "id";
    private static final Application APPLICATION = new Application();
    private static final Scholarship SCHOLARSHIP = new Scholarship(1, "name", null,
            10000D, 10000D, 10000D, 10000D, true,
            true, true);
    private static final StudentFinance STUDENT_FINANCE = new StudentFinance();
    @Mock
    private Dao<String, Application> applicationDao;

    @Mock
    private Dao<Integer, Scholarship> scholarshipDao;

    @Mock
    private Dao<String, StudentFinance> studentFinanceDao;

    @InjectMocks
    MinistryApplicationService ministryApplicationService;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
       ministryApplicationService = new MinistryApplicationService(applicationDao, scholarshipDao, studentFinanceDao);
    }

    @Test
    void shouldGetApplicationWithAmountForSlabA() throws SQLException {
        STUDENT_FINANCE.setAnnualIncome(2000001D);
        when(applicationDao.findById(any())).thenReturn(of(APPLICATION));
        when(scholarshipDao.findById(any())).thenReturn(of(SCHOLARSHIP));
        when(studentFinanceDao.findById(any())).thenReturn(of(STUDENT_FINANCE));
        final Application applicationWithAmount = ministryApplicationService.getApplicationWithAmount(ID);

        assertEquals(6000D, applicationWithAmount.getTuitionAmount());
        assertEquals(10000D, applicationWithAmount.getInsuranceAmount());
        assertEquals(0D, applicationWithAmount.getTravelAmount());
        assertEquals(0D, applicationWithAmount.getLivingExpensesAmount());
    }

    @Test
    void shouldIssueFunds() throws SQLException {
        ministryApplicationService.issueFundToApplication(APPLICATION);

        verify(applicationDao).updateValue(null, "ministry_status", FUND_ISSUED);
        verify(applicationDao).setValues(APPLICATION);
    }
}
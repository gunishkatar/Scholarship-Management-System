package com.dal.group7.service.implementation;

import com.dal.group7.persistent.implementations.ScholarshipDao;
import com.dal.group7.persistent.model.Scholarship;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;

import static java.sql.Date.valueOf;
import static org.mockito.ArgumentMatchers.any;

class MinistryScholarshipServiceTest {

    private static final String FILE_PATH = "file-path";
    private static final Scholarship scholarship = new Scholarship(1, "name", valueOf("2021-12-12"), 5000L, true, true, true);

    @Mock
    private ScholarshipDao scholarshipDao;

    @Mock
    private JsonFileReader jsonFileReader;

    @InjectMocks
    private MinistryScholarshipService ministryScholarshipService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReadFromJsonFileAndSaveScholarship() throws SQLException, IOException {
        Mockito.when(jsonFileReader.readJson(any())).thenReturn(new JSONObject(getSource()));

        ministryScholarshipService.saveScholarship(FILE_PATH);

        Mockito.verify(scholarshipDao).insertOne(scholarship);
    }

    private String getSource() {
        return "{\n" +
                "  \"form_name\": \"CREATE SCHOLARSHIP\",\n" +
                "  \"scholarship_name\": \"name\",\n" +
                "  \"effective_date\": \"2021-12-12\",\n" +
                "  \"scholarship_amount\": 5000,\n" +
                "  \"scholarship_criteria_girl_child\": \"true\",\n" +
                "  \"scholarship_criteria_sports\": \"true\",\n" +
                "  \"scholarship_criteria_academics\": \"true\" \n" +
                "}";
    }
}
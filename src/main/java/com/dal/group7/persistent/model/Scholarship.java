package com.dal.group7.persistent.model;


import org.json.JSONObject;

import java.sql.Date;
import java.util.Random;

public class Scholarship {
    private Integer id;
    private String scholarShipName;
    private Date effectiveDate;
    private Long scholarshipAmount;
    private Boolean criteriaGirlChild;
    private Boolean criteriaSports;
    private Boolean criteriaAcademics;

    public Scholarship() {
    }

    public Scholarship(Integer id, String scholarShipName, Date effectiveDate, Long scholarshipAmount,
                       Boolean criteriaGirlChild, Boolean criteriaSports, Boolean criteriaAcademics) {
        this.id = id;
        this.scholarShipName = scholarShipName;
        this.effectiveDate = effectiveDate;
        this.scholarshipAmount = scholarshipAmount;
        this.criteriaGirlChild = criteriaGirlChild;
        this.criteriaSports = criteriaSports;
        this.criteriaAcademics = criteriaAcademics;
    }

    public Integer getId() {
        return id;
    }

    public String getScholarShipName() {
        return scholarShipName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public Long getScholarshipAmount() {
        return scholarshipAmount;
    }

    public Boolean getCriteriaGirlChild() {
        return criteriaGirlChild;
    }

    public Boolean getCriteriaSports() {
        return criteriaSports;
    }

    public Boolean getCriteriaAcademics() {
        return criteriaAcademics;
    }

    public Scholarship from(JSONObject jsonObject) {
        this.id = new Random().nextInt();
        this.scholarShipName = jsonObject.getString("scholarship_name");
        this.effectiveDate = Date.valueOf(jsonObject.getString("effective_date"));
        this.scholarshipAmount = jsonObject.getLong("scholarship_amount");
        this.criteriaGirlChild = jsonObject.getBoolean("scholarship_criteria_girl_child");
        this.criteriaSports = jsonObject.getBoolean("scholarship_criteria_sports");
        this.criteriaAcademics = jsonObject.getBoolean("scholarship_criteria_academics");
        return this;
    }
}

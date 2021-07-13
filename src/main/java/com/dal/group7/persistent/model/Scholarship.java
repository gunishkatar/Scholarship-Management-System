package com.dal.group7.persistent.model;


import org.json.JSONObject;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

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

    public List<Object> getFieldValues() {
        return stream(this.getClass().getDeclaredFields()).map(field -> {
            try {
                return field.get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scholarship that = (Scholarship) o;
        return Objects.equals(scholarShipName, that.scholarShipName) &&
                Objects.equals(effectiveDate, that.effectiveDate) &&
                Objects.equals(scholarshipAmount, that.scholarshipAmount) &&
                Objects.equals(criteriaGirlChild, that.criteriaGirlChild) &&
                Objects.equals(criteriaSports, that.criteriaSports) &&
                Objects.equals(criteriaAcademics, that.criteriaAcademics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scholarShipName, effectiveDate, scholarshipAmount, criteriaGirlChild, criteriaSports,
                criteriaAcademics);
    }
}

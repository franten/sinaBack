package com.alfatecsistemas.sina.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORMA_PROFESSIONALS")
public class OrmaProfessionals {

    @Id
    @GeneratedValue
    @Column(name="PROF_ID")
    private Integer profId;

    @Column(name="PROF_NAME")
    private String profName;

    @Column(name="PROF_SURNAME_1")
    private String profSurname1;

    @Column(name="PROF_SURNAME_2")
    private String profSurname2;

    @Column(name="PROF_ID_VALUE")
    private String profIdValue;

    @Column(name="PROF_DELETED")
    private Boolean profDeleted;

    @Column(name="PROF_DELETED_DATE")
    private LocalDateTime profDeletedDate;

    public Integer getProfId() {
        return profId;
    }

    public void setProfId(Integer profId) {
        this.profId = profId;
    }

    public String getProfName() {
        return profName;
    }

    public void setProfName(String profName) {
        this.profName = profName;
    }

    public String getProfSurname1() {
        return profSurname1;
    }

    public void setProfSurname1(String profSurname1) {
        this.profSurname1 = profSurname1;
    }

    public String getProfSurname2() {
        return profSurname2;
    }

    public void setProfSurname2(String profSurname2) {
        this.profSurname2 = profSurname2;
    }

    public String getProfIdValue() {
        return profIdValue;
    }

    public void setProfIdValue(String profIdValue) {
        this.profIdValue = profIdValue;
    }

    public Boolean getProfDeleted() {
        return profDeleted;
    }

    public void setProfDeleted(Boolean profDeleted) {
        this.profDeleted = profDeleted;
    }

    public LocalDateTime getProfDeletedDate() {
        return profDeletedDate;
    }

    public void setProfDeletedDate(LocalDateTime profDeletedDate) {
        this.profDeletedDate = profDeletedDate;
    }
}

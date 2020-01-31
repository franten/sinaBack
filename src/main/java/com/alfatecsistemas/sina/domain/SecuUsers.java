package com.alfatecsistemas.sina.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SECU_USERS")
public class SecuUsers implements Serializable {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private Integer userId;

    @Column(name="PROF_ID")
    private Integer profId;

    @Column(name="USER_LOGIN")
    private String userLogin;

    @Column(name="USER_PASSWORD")
    private String userPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrmaProfessionals ormaProfessionals;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProfId() {
        return profId;
    }

    public void setProfId(Integer profId) {
        this.profId = profId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public OrmaProfessionals getOrmaProfessionals() {
        return ormaProfessionals;
    }

    public void setOrmaProfessionals(OrmaProfessionals ormaProfessionals) {
        this.ormaProfessionals = ormaProfessionals;
    }
}

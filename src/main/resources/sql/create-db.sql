-- ORMA_PROFESIONALS

CREATE SEQUENCE SEQU_ORMA_PROFESIONALS;

CREATE TABLE ORMA_PROFESSIONALS (
	PROF_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_ORMA_PROFESIONALS) NOT NULL AUTO_INCREMENT,
	PROF_NAME VARCHAR(50) NOT NULL,
	PROF_SURNAME_1 VARCHAR(50),
	PROF_SURNAME_2 VARCHAR(50),
	PROF_ID_VALUE VARCHAR(100) NOT NULL,
	PROF_DELETED BOOLEAN NOT NULL,
    PROF_DELETED_DATE TIMESTAMP,
	CONSTRAINT PK_PROFESSIONALS PRIMARY KEY (PROF_ID)
);
CREATE UNIQUE INDEX IDX_PK_OP_PROF_ID ON ORMA_PROFESSIONALS (PROF_ID);

-- ORMA_CENTERS

CREATE SEQUENCE SEQU_ORMA_CENTERS;

CREATE TABLE ORMA_CENTERS (
	CENT_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_ORMA_CENTERS) NOT NULL AUTO_INCREMENT,
	CENT_CODE VARCHAR(50) NOT NULL,
	CENT_NAME VARCHAR(50),
	CENT_DELETED BOOLEAN NOT NULL,
	CENT_DELETED_DATE TIMESTAMP NULL,
	CONSTRAINT PK_CENT PRIMARY KEY (CENT_ID)
);
CREATE UNIQUE INDEX IDX_PK_OC_CENT_ID ON ORMA_CENTERS (CENT_ID);

-- ORMA_SERVICES

CREATE SEQUENCE SEQU_ORMA_SERVICE;

CREATE TABLE ORMA_SERVICES (
	SERV_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_ORMA_SERVICE) NOT NULL AUTO_INCREMENT,
	SERV_CODE VARCHAR(50) NOT NULL,
	SERV_DESCRIPTION VARCHAR(50),
	SERV_DELETED BOOLEAN NOT NULL,
	SERV_DELETED_DATE TIMESTAMP NULL,
	CONSTRAINT PK_SERV PRIMARY KEY (SERV_ID)
);
CREATE UNIQUE INDEX IDX_PK_OS_SERV_ID ON ORMA_SERVICES (SERV_ID);

-- ORMA_AMBITS

CREATE SEQUENCE SEQU_ORMA_AMBITS;

CREATE TABLE ORMA_AMBITS (
	AMBI_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_ORMA_AMBITS) NOT NULL AUTO_INCREMENT,
	AMBI_CODE VARCHAR(50) NOT NULL,
	AMBI_DESCRIPTION VARCHAR(50),
	AMBI_DELETED BOOLEAN NOT NULL,
	AMBI_DELETED_DATE TIMESTAMP NULL,
	CONSTRAINT PK_AMBI PRIMARY KEY (AMBI_ID)
);
CREATE UNIQUE INDEX IDX_PK_OS_AMBI_ID ON ORMA_AMBITS (AMBI_ID);

-- SECU_USERS

CREATE SEQUENCE SEQU_SECU_USERS;

CREATE TABLE SECU_USERS (
	USER_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_SECU_USERS) NOT NULL AUTO_INCREMENT,
	PROF_ID INTEGER NOT NULL,
	USER_LOGIN VARCHAR(100) NOT NULL,
	USER_PASSWORD VARCHAR(50) NOT NULL,
	CONSTRAINT PK_USER PRIMARY KEY (USER_ID),
	CONSTRAINT FK_USER FOREIGN KEY (PROF_ID) REFERENCES ORMA_PROFESSIONALS(PROF_ID)
);
CREATE UNIQUE INDEX IDX_PK_SU_USER_ID ON SECU_USERS (USER_ID);
CREATE INDEX IDX_FK_SU_PROF_ID ON SECU_USERS (PROF_ID);

-- SECU_ROLES

CREATE SEQUENCE SEQU_SECU_ROLES;

CREATE TABLE SECU_ROLES (
	SROL_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_SECU_ROLES) NOT NULL AUTO_INCREMENT,
	SROL_CODE VARCHAR(50) NOT NULL,
	SROL_DESCRIPTION VARCHAR(50),
	SROL_DELETED BOOLEAN NOT NULL,
	SROL_DELETED_DATE TIMESTAMP NULL,
	CONSTRAINT PK_SROL PRIMARY KEY (SROL_ID)
);
CREATE UNIQUE INDEX IDX_PK_SR_SROL_ID ON SECU_ROLES (SROL_ID);

-- SEQU_CONFIG_ROLES

CREATE SEQUENCE SEQU_CONFIG_ROLES;

CREATE TABLE SECU_CONFIG_ROLES (
	SCRO_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_CONFIG_ROLES) NOT NULL AUTO_INCREMENT,
	SROL_ID INTEGER NOT NULL,
	AMBI_ID INTEGER NOT NULL,
	CENT_ID INTEGER NOT NULL,
	SERV_ID INTEGER NOT NULL,
	CONSTRAINT PK_SCRO PRIMARY KEY (SCRO_ID),
	CONSTRAINT FK_SCRO_AMBI_01 FOREIGN KEY (AMBI_ID) REFERENCES ORMA_AMBITS(AMBI_ID),
	CONSTRAINT FK_SCRO_CENT_01 FOREIGN KEY (CENT_ID) REFERENCES ORMA_CENTERS(CENT_ID),
	CONSTRAINT FK_SCRO_SERV_01 FOREIGN KEY (SERV_ID) REFERENCES ORMA_SERVICES(SERV_ID),
	CONSTRAINT FK_SCRO_SROL_01 FOREIGN KEY (SROL_ID) REFERENCES SECU_ROLES(SROL_ID)
);
CREATE UNIQUE INDEX IDX_PK_SCR_SCRO_ID ON SECU_CONFIG_ROLES (SCRO_ID);

-- SEQU_PROFESSIONAL_ROLES

CREATE SEQUENCE SEQU_PROFESSIONAL_ROLES;

CREATE TABLE SECU_PROFESSIONAL_ROLES (
	USRO_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_PROFESSIONAL_ROLES) NOT NULL AUTO_INCREMENT,
	PROF_ID INTEGER NOT NULL,
	SCRO_ID INTEGER NOT NULL,
	USRO_FAVOURITE BOOLEAN NOT NULL,
	CONSTRAINT PK_USRO PRIMARY KEY (USRO_ID),
	CONSTRAINT UK_USRO_01_NEW UNIQUE (PROF_ID,SCRO_ID),
	CONSTRAINT FK_USRO_PROF_01 FOREIGN KEY (PROF_ID) REFERENCES ORMA_PROFESSIONALS(PROF_ID),
	CONSTRAINT FK_USRO_SCRO_01 FOREIGN KEY (SCRO_ID) REFERENCES SECU_CONFIG_ROLES(SCRO_ID)
);
CREATE UNIQUE INDEX IDX_PK_SPR_USRO_ID ON SECU_PROFESSIONAL_ROLES (USRO_ID);

-- CONF_ACTIONS

CREATE SEQUENCE SEQU_CONF_ACTIONS;

CREATE TABLE CONF_ACTIONS (
	SIAC_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_CONF_ACTIONS) NOT NULL AUTO_INCREMENT,
	SIAC_NAME VARCHAR(100) NOT NULL,
	SIAC_DESCRIPTION VARCHAR(100),
	SIAC_DELETED BOOLEAN NOT NULL,
	SIAC_DELETED_DATE TIMESTAMP NULL,
	CONSTRAINT PK_SIAC PRIMARY KEY (SIAC_ID),
	CONSTRAINT UK_SIAC_01 UNIQUE (SIAC_NAME)
);
CREATE UNIQUE INDEX IDX_PK_CA_SIAC_ID ON CONF_ACTIONS (SIAC_ID);

-- CONF_STATION_STRUCTURES

CREATE SEQUENCE SEQU_CONF_STATION_STRUCTURES;

CREATE TABLE CONF_STATION_STRUCTURES (
	CSTS_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_CONF_STATION_STRUCTURES) NOT NULL AUTO_INCREMENT,
	SIAC_ID INTEGER NOT NULL,
	CSTS_DESCRIPTION VARCHAR(40) NULL,
	CSTS_LONG_DESCRIPTION VARCHAR(75) NULL,
	CSTS_CODE VARCHAR(10) NOT NULL,
	CONSTRAINT CSTS_CODE_UK UNIQUE (CSTS_CODE),
	CONSTRAINT PK_CSTS PRIMARY KEY (CSTS_ID),
	CONSTRAINT FK_CSTS_SIAC_ID FOREIGN KEY (SIAC_ID) REFERENCES CONF_ACTIONS(SIAC_ID)
);
CREATE UNIQUE INDEX IDX_PK_CSS_SIAC_ID ON CONF_STATION_STRUCTURES (SIAC_ID);

-- SECU_PROF_ROLE_ACTIONS

CREATE SEQUENCE SEQU_SECU_PROF_ROLE_ACTIONS;

CREATE TABLE SECU_PROF_ROLE_ACTIONS (
	SPRA_ID INTEGER DEFAULT (NEXT VALUE FOR SEQU_SECU_PROF_ROLE_ACTIONS) NOT NULL AUTO_INCREMENT,
	PROF_ID INTEGER NOT NULL,
	SCRO_ID INTEGER NOT NULL,
	SIAC_ID INTEGER NOT NULL,
	CONSTRAINT PK_SPRA PRIMARY KEY (SPRA_ID),
	CONSTRAINT UK_SPRA_01 UNIQUE (PROF_ID,SCRO_ID,SIAC_ID),
	CONSTRAINT FK_SPRA_PROF_01 FOREIGN KEY (PROF_ID) REFERENCES ORMA_PROFESSIONALS(PROF_ID),
	CONSTRAINT FK_SPRA_SCRO_01 FOREIGN KEY (SCRO_ID) REFERENCES SECU_CONFIG_ROLES(SCRO_ID),
	CONSTRAINT FK_SPRA_SIAC_01 FOREIGN KEY (SIAC_ID) REFERENCES CONF_ACTIONS(SIAC_ID)
);
CREATE UNIQUE INDEX IDX_PK_SPRA_SIAC_ID ON SECU_PROF_ROLE_ACTIONS (SPRA_ID);


SELECT
<<<<<<< HEAD
	css.CSTS_ID,
	css.CSTS_DESCRIPTION
FROM
	CONF_STATION_STRUCTURES css
INNER JOIN SECU_PROF_ROLE_ACTIONS spra ON
	css.SIAC_ID = spra.SIAC_ID
INNER JOIN SECU_USERS su ON
	spra.PROF_ID = su.PROF_ID
INNER JOIN SECU_PROFESSIONAL_ROLES spr ON
	spra.SCRO_ID = spr.SCRO_ID	AND spra.PROF_ID = spr.PROF_ID
WHERE
	su.USER_LOGIN = 'abernal'
	AND spr.USRO_FAVOURITE = 1;
=======
	css.CSTS_ID , css.CSTS_DESCRIPTION
FROM
	CONF_STATION_STRUCTURES css
INNER JOIN SECU_PROF_ROLE_ACTIONS spra ON
	css.SIAC_ID = SPRA.SIAC_ID
INNER JOIN SECU_USERS su ON
	spra.PROF_ID = su.PROF_ID
INNER JOIN SECU_PROFESSIONAL_ROLES spr ON
	spra.SCRO_ID = spr.SCRO_ID
	AND spra.PROF_ID = spr.PROF_ID
WHERE
	su.USER_LOGIN = 'abernal'
	AND SPR.USRO_FAVOURITE = 1
>>>>>>> masterlalego

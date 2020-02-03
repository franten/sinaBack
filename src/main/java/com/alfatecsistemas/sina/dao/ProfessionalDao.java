package com.alfatecsistemas.sina.dao;

import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.dto.FilterProfessionalsDto;

import java.util.List;

public interface ProfessionalDao {

    List<OrmaProfessionals> findAll(FilterProfessionalsDto filterProfessionals);

    OrmaProfessionals findOne(Integer profId);
}

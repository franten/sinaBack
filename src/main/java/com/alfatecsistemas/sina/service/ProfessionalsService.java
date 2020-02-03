package com.alfatecsistemas.sina.service;

import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.domain.SecuUsers;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public interface ProfessionalsService {

    List<OrmaProfessionals> getProfessionals(Map<String, String> params);

    OrmaProfessionals getProfessional(Integer profId);
}

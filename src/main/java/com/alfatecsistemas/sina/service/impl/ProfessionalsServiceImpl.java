package com.alfatecsistemas.sina.service.impl;

import com.alfatecsistemas.sina.dao.ProfessionalDao;
import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.dto.FilterProfessionalsDto;
import com.alfatecsistemas.sina.service.ProfessionalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class ProfessionalsServiceImpl implements ProfessionalsService {

    @Autowired
    private ProfessionalDao professionalDao;

    public List<OrmaProfessionals> getProfessionals(Map<String, String> params) {

        final FilterProfessionalsDto filterProfessionals = maperProfessionals(params);

        return professionalDao.findAll(filterProfessionals);
    }

    public OrmaProfessionals getProfessional(Integer profId) {

        return professionalDao.findOne(profId);
    }

    private static FilterProfessionalsDto maperProfessionals(final Map<String, String> params) {
        final FilterProfessionalsDto maperProfessionals = new FilterProfessionalsDto();

        maperProfessionals.setName(trimEmpty(params, "name"));
        maperProfessionals.setSurname1(trimEmpty(params, "surname1"));
        maperProfessionals.setSurname2(trimEmpty(params, "surname2"));
        maperProfessionals.setIdValue(trimEmpty(params, "idValue"));

        return maperProfessionals;
    }

    private static String trimEmpty(final Map<String, String> params, final String name) {
        return StringUtils.isEmpty(params.get(name)) ? null : params.get(name);
    }

}

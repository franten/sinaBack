package com.alfatecsistemas.sina.controller;

import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.service.ProfessionalsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfessionalsController.class);

    @Autowired
    private ProfessionalsService professionalsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<OrmaProfessionals> getProfessionals(@RequestParam final Map<String, String> params) {

        return professionalsService.getProfessionals(params);
    }

    @RequestMapping(path = "/{profId}", method = RequestMethod.GET)
    public OrmaProfessionals getProfessional(@PathVariable Integer profId) {
        return professionalsService.getProfessional(profId);
    }
}

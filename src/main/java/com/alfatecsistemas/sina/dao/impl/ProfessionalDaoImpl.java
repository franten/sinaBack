package com.alfatecsistemas.sina.dao.impl;

import com.alfatecsistemas.sina.dao.ProfessionalDao;
import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.dto.FilterProfessionalsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessionalDaoImpl implements ProfessionalDao {

    @Autowired
    EntityManager em;

    public List<OrmaProfessionals> findAll(FilterProfessionalsDto filterProfessionals) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrmaProfessionals> cq = cb.createQuery(OrmaProfessionals.class);

        Root<OrmaProfessionals> root = cq.from(OrmaProfessionals.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filterProfessionals.getName() != null) {
            predicates.add(cb.equal(root.get("profName"), filterProfessionals.getName()));
        }
        if (filterProfessionals.getSurname1() != null) {
            predicates.add(cb.equal(root.get("profSurname1"), filterProfessionals.getSurname1()));
        }
        if (filterProfessionals.getSurname2() != null) {
            predicates.add(cb.equal(root.get("profSurname2"), filterProfessionals.getSurname2()));
        }
        if (filterProfessionals.getIdValue() != null) {
            predicates.add(cb.like(root.get("profIdValue"), "%" + filterProfessionals.getIdValue() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    public OrmaProfessionals findOne(Integer profId) {

        return em.find(OrmaProfessionals.class, profId);
    }
}

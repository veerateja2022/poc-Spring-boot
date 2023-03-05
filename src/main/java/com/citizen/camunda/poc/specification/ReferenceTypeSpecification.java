package com.citizen.camunda.poc.specification;

import com.citizen.camunda.poc.entity.ReferenceType;
import com.citizen.camunda.poc.model.ReferenceTypeSearchModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ReferenceTypeSpecification implements Specification<ReferenceType> {

    private ReferenceTypeSearchModel criteria;

    public ReferenceTypeSpecification(ReferenceTypeSearchModel searchModel) {
        this.criteria = searchModel;
    }

    @Override
    public Predicate toPredicate(Root<ReferenceType> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(
                root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
    }
}

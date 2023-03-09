package com.citizen.camunda.poc.repo;

import com.citizen.camunda.poc.entity.ReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReferenceTypeRepository extends JpaRepository<ReferenceType, Long>, JpaSpecificationExecutor<ReferenceType> {
    List<ReferenceType> findByReferenceTypeAndReferenceTypeDesc(String referenceType, String referenceTypeDesc);

    @Transactional
    @Modifying
    void deleteByReferenceTypeAndReferenceTypeDesc(String referenceType, String referenceTypeDesc);
}

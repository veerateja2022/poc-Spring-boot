package com.citizen.camunda.poc.repo;

import com.citizen.camunda.poc.entity.ReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceTypeRepository extends JpaRepository<ReferenceType, Long>, JpaSpecificationExecutor<ReferenceType> {

}

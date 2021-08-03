package com.sgav.sgav.repository;

import com.sgav.sgav.model.Clubhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClubhouseRepository extends JpaRepository<Clubhouse, Integer>, JpaSpecificationExecutor<Clubhouse> {

}
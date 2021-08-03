package com.sgav.sgav.repository;

import com.sgav.sgav.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LoginRepository extends JpaRepository<Login, Integer>, JpaSpecificationExecutor<Login> {

}
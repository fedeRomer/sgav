package com.sgav.sgav.repository.login;

import com.sgav.sgav.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>, JpaSpecificationExecutor<Login> {

}
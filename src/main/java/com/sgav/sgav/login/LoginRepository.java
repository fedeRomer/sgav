package com.sgav.sgav.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    @Query(value = "SELECT * FROM login WHERE username = ?", nativeQuery = true)
    Login findLoginByUsername(String username);
}
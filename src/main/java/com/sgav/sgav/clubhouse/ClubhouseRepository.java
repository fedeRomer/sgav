package com.sgav.sgav.clubhouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClubhouseRepository extends JpaRepository<Clubhouse, Integer>, JpaSpecificationExecutor<Clubhouse> {

    @Query(value = "SELECT * FROM clubhouse WHERE nombre LIKE '%:string%'", nativeQuery = true)
    Clubhouse findClubhouseByNombre(String string);
}
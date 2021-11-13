package com.sgav.sgav.visitante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface VisitanteRepository extends JpaRepository<Visitante, Integer>, JpaSpecificationExecutor<Visitante> {

    @Query(value = "SELECT * FROM Visitante WHERE nombre LIKE '%:string%'", nativeQuery = true)
    List<Visitante> findVisitanteByNombre(String string);

    @Query(value = "SELECT * FROM Visitante WHERE apellido LIKE '%:string%'", nativeQuery = true)
    List<Visitante> findVisitanteByApellido(String string);

    @Query(value = "SELECT * FROM Visitante  WHERE dni = ?", nativeQuery = true)
    List<Visitante> findVisitanteByDni(Integer dni);

    @Query(value = "SELECT * FROM Visitante  WHERE unidad_funcional_id = ?", nativeQuery = true)
    List<Visitante> findVisitanteByUnidadFuncionalId(Integer unidad_funcional_id);

    @Query(value = "SELECT * FROM Visitante WHERE fecha_entrada >= :firstDate and fecha_salida <= :secondDate ", nativeQuery = true)
    List<Visitante> findAllVisitanteBetweenDates(Date firstDate, Date secondDate);

    @Query(value = "SELECT * FROM Visitante WHERE fecha_entrada >= :firstDate", nativeQuery = true)
    List<Visitante> findAllVisitanteFromDate(Date firstDate);

    @Query(value = "SELECT * FROM Visitante WHERE fecha_salida <= :secondDate", nativeQuery = true)
    List<Visitante> findAllVisitanteToDate(Date secondDate);
}
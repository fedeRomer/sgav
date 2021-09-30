package com.sgav.sgav.mascotasPerdidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MascotasPerdidasService {

    @Autowired
    private MascotasPerdidasRepository mascotasPerdidasRepository;

    public ResponseEntity<?> getMascota(MascotasPerdidas mascotasPerdidas) {

        MascotasPerdidas mp = new MascotasPerdidas();
        List<MascotasPerdidas> mascotasPerdidasList = new ArrayList<>();
        Optional<MascotasPerdidas> repoResponse = Optional.of(new MascotasPerdidas());

        if (mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0 || !mascotasPerdidas.getTitulo().isEmpty()) {
            mascotasPerdidasList = mascotasPerdidasRepository.findMascotasPerdidasByTitulo(mascotasPerdidas.getTitulo());
            if (mascotasPerdidasList.isEmpty()) {
                return ResponseEntity.badRequest().body("No se encontraron resultados");
            } else {
                return new ResponseEntity<>(mascotasPerdidasList, HttpStatus.OK);
            }
        } else if (mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0 || !mascotasPerdidas.getDetalle().isEmpty()) {
            mascotasPerdidasList = mascotasPerdidasRepository.findMascotasPerdidasByDetalle(mascotasPerdidas.getDetalle());
            if (mascotasPerdidasList.isEmpty()) {
                return ResponseEntity.badRequest().body("No se encontraron resultados");
            } else {
                return new ResponseEntity<>(mascotasPerdidasList, HttpStatus.OK);
            }
        }

        repoResponse = mascotasPerdidasRepository.findById(mascotasPerdidas.getId());
        if (repoResponse.get().getId() == null || repoResponse.get().getId() == 0) {
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }

        return new ResponseEntity<>(repoResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllMascotasPerdidas() {

        List<MascotasPerdidas> mascotasPerdidasList = new ArrayList<>();

        mascotasPerdidasList = mascotasPerdidasRepository.findAll();
        if (mascotasPerdidasList.isEmpty()) {
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }
        return new ResponseEntity<>(mascotasPerdidasList, HttpStatus.OK);
    }

    public ResponseEntity<?> addMascotaPerdida(MascotasPerdidas mascotasPerdidas) {

        if(mascotasPerdidas.getTitulo().isEmpty() || mascotasPerdidas.getTitulo() == null){
            return ResponseEntity.badRequest().body("Titulo faltante");
        }
        if (mascotasPerdidas.getDetalle().isEmpty() || mascotasPerdidas.getDetalle() == null){
            return ResponseEntity.badRequest().body("Detalle faltante");
        }
        if(mascotasPerdidas.getEncontrado() == null){
            mascotasPerdidas.setEncontrado(false);
        }

        mascotasPerdidasRepository.save(mascotasPerdidas);
        return new ResponseEntity<>("Mascota añadida exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> updateMascotaPerdida(MascotasPerdidas mascotasPerdidas) {

        if(mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        mascotasPerdidasRepository.save(mascotasPerdidas);
        return new ResponseEntity<>("Mascota actualizada exitosamente",HttpStatus.OK);
    }

    public ResponseEntity<?> deleteMascotaPerdida(MascotasPerdidas mascotasPerdidas) {

        if(mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        mascotasPerdidasRepository.delete(mascotasPerdidas);
        return new ResponseEntity<>("Mascota eliminada exitosamente", HttpStatus.OK);
    }
}

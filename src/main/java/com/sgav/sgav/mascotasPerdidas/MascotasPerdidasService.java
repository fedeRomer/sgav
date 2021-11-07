package com.sgav.sgav.mascotasPerdidas;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MascotasPerdidasService {

    @Autowired
    private MascotasPerdidasRepository mascotasPerdidasRepository;

    ResponseCustom responseCustom = new ResponseCustom();

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

        mascotasPerdidasList = mascotasPerdidasRepository.findAllMascotasPerdidasNoEncontradas("t");

        if (mascotasPerdidasList.isEmpty()) {
            responseCustom.setResponse("No se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.OK);
        }

        for(int i=0; i< mascotasPerdidasList.size(); i++){
            if(mascotasPerdidasList.get(i).getEncontrado()){
                mascotasPerdidasList.remove(i);
            }
        }

        return new ResponseEntity<>(mascotasPerdidasList, HttpStatus.OK);
    }

    public ResponseEntity<?> addMascotaPerdida(MascotasPerdidas mascotasPerdidas) {


        if(mascotasPerdidas.getTitulo().isEmpty() || mascotasPerdidas.getTitulo() == null){
            responseCustom.setResponse("Titulo faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(mascotasPerdidas.getTitulo())){
                responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if (mascotasPerdidas.getDetalle().isEmpty() || mascotasPerdidas.getDetalle() == null){
            responseCustom.setResponse("Detalle faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(mascotasPerdidas.getDetalle())){
                responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(mascotasPerdidas.getEncontrado() == null){
            mascotasPerdidas.setEncontrado(false);
        }

        mascotasPerdidasRepository.save(mascotasPerdidas);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> updateMascotaPerdida(MascotasPerdidas mascotasPerdidas) {

        if(mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!Helper.isNullOrEmpty(mascotasPerdidas.getTitulo())){
           if(!Helper.isValidStringWithNumbers(mascotasPerdidas.getTitulo())){
               responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
               return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isNullOrEmpty(mascotasPerdidas.getDetalle())){
          if(!Helper.isValidStringWithNumbers(mascotasPerdidas.getDetalle())){
              responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
              return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(mascotasPerdidas.getEncontrado() == null){
            mascotasPerdidas.setEncontrado(false);
        }

        mascotasPerdidasRepository.save(mascotasPerdidas);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteMascotaPerdida(MascotasPerdidas mascotasPerdidas) {

        if(mascotasPerdidas.getId() == null || mascotasPerdidas.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        mascotasPerdidasRepository.delete(mascotasPerdidas);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> uploadImage(String mascota, MultipartFile file) {
        MascotasPerdidas m= new MascotasPerdidas();
        try{
            mascota.toString();
        }catch(Exception e){
            System.out.println("e.printStackTrace()");
        }

        return new ResponseEntity<>("",HttpStatus.NOT_IMPLEMENTED);
    }
}

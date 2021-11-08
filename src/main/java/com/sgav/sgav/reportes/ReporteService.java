package com.sgav.sgav.reportes;

import com.sgav.sgav.notificationExpensa.NotificationExpensa;
import com.sgav.sgav.notificationExpensa.NotificationExpensaRepository;
import com.sgav.sgav.notificationMulta.NotificationMulta;
import com.sgav.sgav.notificationMulta.NotificationMultaRepository;
import com.sgav.sgav.sos.Alerta;
import com.sgav.sgav.sos.AlertaRepository;
import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import com.sgav.sgav.visitante.Visitante;
import com.sgav.sgav.visitante.VisitanteRepository;
import com.sgav.sgav.visitanteVehiculo.VisitanteVehiculo;
import com.sgav.sgav.visitanteVehiculo.VisitanteVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private VisitanteRepository visitanteRepository;

    @Autowired
    private VisitanteVehiculoRepository visitanteVehiculoRepository;

    @Autowired
    private NotificationExpensaRepository notificationExpensaRepository;

    @Autowired
    private NotificationMultaRepository notificationMultaRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    public ResponseEntity<?> generateReport(ReporteDTO reporteDTO) {

        ResponseCustom responseCustom = new ResponseCustom();

        Boolean searchOnlyFromDateToToday = false;
        Boolean searchOnlyToDate = false;
        Boolean searchToAndFromDate = false;
        Boolean searchOnlyTheSameDate = false;
        Boolean searchAll = false;

        if(Helper.isNullOrEmpty(reporteDTO.getTypeOfReport())){
            responseCustom.setResponse("Se requiere el campo Tipo para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(reporteDTO.getFromDate() == null && reporteDTO.getToDate() == null){
            searchAll = true;
        }



        if(reporteDTO.getFromDate() != null){
            if(reporteDTO.getToDate() != null){
                if(reporteDTO.getFromDate() == reporteDTO.getToDate()){
                    searchOnlyTheSameDate = true;
                }else{
                    searchToAndFromDate = true;
                }
            }else{
                searchOnlyFromDateToToday=true;
            }
        }

        if(reporteDTO.getTypeOfReport().equalsIgnoreCase("VISITAS")){
            List<Visitante> visitanteList = new ArrayList<>();

            visitanteList = visitanteRepository.findAll();

            return new ResponseEntity<>(visitanteList, HttpStatus.OK);


        } else if (reporteDTO.getTypeOfReport().equalsIgnoreCase("VISITASVEHICULO")) {

            List<VisitanteVehiculo> visitanteVehiculos = new ArrayList<>();
            visitanteVehiculos = visitanteVehiculoRepository.findAll();
            return new ResponseEntity<>(visitanteVehiculos, HttpStatus.OK);
        } else if (reporteDTO.getTypeOfReport().equalsIgnoreCase("MULTAS")) {

            List<NotificationMulta> notificationMultas = new ArrayList<>();
            notificationMultas = notificationMultaRepository.findAll();
            return new ResponseEntity<>(notificationMultas, HttpStatus.OK);
        } else if (reporteDTO.getTypeOfReport().equalsIgnoreCase("EXPENSAS")) {

            List<NotificationExpensa> notificationExpensas = new ArrayList<>();
            notificationExpensas = notificationExpensaRepository.findAll();
            return new ResponseEntity<>(notificationExpensas, HttpStatus.OK);
        } else if (reporteDTO.getTypeOfReport().equalsIgnoreCase("SOS")) {

            List<Alerta> alertaList = new ArrayList<>();
            alertaList = alertaRepository.findAll();
            return new ResponseEntity<>(alertaList, HttpStatus.OK);
        }



        return new ResponseEntity<>("", HttpStatus.NOT_IMPLEMENTED);

    }
}

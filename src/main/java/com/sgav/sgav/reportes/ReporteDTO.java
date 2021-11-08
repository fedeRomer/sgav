package com.sgav.sgav.reportes;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReporteDTO {

    private Date fromDate;
    private Date toDate;
    private String typeOfReport;
    private List<Object> responseObj;
}

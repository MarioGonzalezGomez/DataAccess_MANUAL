package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
    private String idProyecto;
    private String nombre;
    private String idjefeProyecto;
    private double presupuesto;
    private Date fechaInicio;
    private Date fechaFin;


}

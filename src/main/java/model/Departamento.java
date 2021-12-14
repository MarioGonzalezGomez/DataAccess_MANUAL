package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//De todos los objetos que hubiera en DTO habría que crear aqui solo su id.
//Nunca objetos
public class Departamento {
    private String id;
    private String nombre;
    private String idJefeDepartamento;
    private Double presupuesto;
    private Double presupuestoAnual;


}



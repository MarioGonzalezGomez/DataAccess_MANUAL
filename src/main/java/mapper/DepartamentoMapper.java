package mapper;

import dto.DepartamentoDTO;
import model.Departamento;

import java.util.stream.Collectors;

public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {
    @Override
    public Departamento fromDTO(DepartamentoDTO item) {
        return Departamento.builder()
                .id(item.getId())
                .nombre(item.getNombre())
               // .idJefeDepartamento(item.getIdJefe())
                .presupuesto(item.getPresupuesto())
                .presupuestoAnual(item.getPresupuestoAnual())
                .build();
    }

    @Override
    public DepartamentoDTO toDTO(Departamento item) {
        return DepartamentoDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                //.idJefe(item.getIdJefeDepartamento())
                .presupuesto(item.getPresupuesto())
                .presupuestoAnual(item.getPresupuestoAnual())
                .build();
    }

    }


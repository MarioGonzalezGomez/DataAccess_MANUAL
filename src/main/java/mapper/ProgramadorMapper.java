package mapper;


import dto.ProgramadorDTO;
import model.Programador;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Programador fromDTO(ProgramadorDTO item) {
        return Programador.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .fechaAlta(item.getFecha_alta())
                .salario(item.getSueldo())
                .contraseña(item.getContraseña())
                .idDepartamento(item.getIdDepartamento())
                .build();
    }


    @Override
    public ProgramadorDTO toDTO(Programador item) {
        return ProgramadorDTO.builder()
                .id(item.getId())
                .nombre(item.getNombre())
                .fecha_alta(item.getFechaAlta())
                .sueldo(item.getSalario())
                .idDepartamento(item.getIdDepartamento())
                .build();
    }
}



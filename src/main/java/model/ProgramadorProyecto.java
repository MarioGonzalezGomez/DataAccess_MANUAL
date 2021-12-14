package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
public class ProgramadorProyecto {

    private UUID idProgamadorProyecto;
    private UUID idProgramador;
    private UUID idProyecto;


}

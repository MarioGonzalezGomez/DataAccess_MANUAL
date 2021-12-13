package service;

import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import mapper.ProgramadorMapper;
import mapper.ProyectoMapper;
import model.Departamento;
import model.Programador;
import model.Proyecto;
import repository.DepartamentoRepository;
import repository.ProgramadorRepository;
import repository.ProyectoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProgramadorService extends BaseService<Programador, String, ProgramadorRepository> {


    ProgramadorMapper mapper = new ProgramadorMapper();

    public ProgramadorService(ProgramadorRepository repository) {
        super(repository);
    }

    public List<ProgramadorDTO> getAllProgramadores() throws SQLException {

        List<Programador> programadores = this.findAll();
        List<ProgramadorDTO> result = new ArrayList<>();

        // Ahora debemos añadir al DTO el Departamento como objeto y la lista de Proyectos,
        // no como ID que es lo que nos viene de la BD
        for (Programador programador : programadores) {
            ProgramadorDTO programadorDTO = mapper.toDTO(programador);
            programadorDTO.setDepartamento(this.getDepartamentoById(programador.getIdDepartamento()));

            // Tenemos que cargar los proyectos que tenga
            programadorDTO.setProyectos(getProgramadorProyectos(programadorDTO.getId()));
            result.add(programadorDTO);

        }
        return result;
    }

    private Departamento getDepartamentoById(String id) throws SQLException {
        DepartamentoService service = new DepartamentoService(new DepartamentoRepository());
        return service.getById(id);
    }

    private Set<Proyecto> getProgramadorProyectos(String id) throws SQLException {
        ProyectoService service = new ProyectoService(new ProyectoRepository());
        return service.getAllProyectosByProgramador(id);
    }


    public ProgramadorDTO getProgramadorById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }


    public ProgramadorDTO postProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador res = this.save(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(res);
    }

    public ProgramadorDTO updateProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador res = this.update(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(res);
    }

    public ProgramadorDTO deleteProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Programador res = this.delete(mapper.fromDTO(programadorDTO));
        return mapper.toDTO(res);
    }

    public Set<Programador> getJefesByDepartment(String nombreDepartamento) throws SQLException {
        ProgramadorRepository repo = new ProgramadorRepository();

        return repo.getJefesByDepartment(nombreDepartamento);
    }


}

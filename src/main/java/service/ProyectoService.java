package service;

import dto.ProyectoDTO;
import mapper.ProyectoMapper;
import model.Programador;
import model.Proyecto;
import repository.ProgramadorRepository;
import repository.ProyectoRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProyectoService extends BaseService<Proyecto, String, ProyectoRepository> {

    ProyectoMapper mapper = new ProyectoMapper();

    public ProyectoService(ProyectoRepository repository) {
        super(repository);
    }

    // Otras operaciones o especificaciones para CRUD
    // O podíamos mapear el nombre
    // O simplemente ocultar las que no queramos usar en niveles superiores
    // Utilizamos los DTO para par datos del servico al controlador que los presenta
    public List<ProyectoDTO> getAllProyectos() throws SQLException {

        List<Proyecto> proyectos = this.findAll();
        List<ProyectoDTO> result = new ArrayList<>();

        // Ahora debemos añadir al DTO el Jefe como objeto y la Categoria,
        // no como ID que es lo que nos viene de la BD
        for (Proyecto proyecto : proyectos) {
            ProyectoDTO proyectoDTO = mapper.toDTO(proyecto);
            proyectoDTO.setJefeProyecto(this.getJefeById(proyecto.getIdjefeProyecto()));
            result.add(proyectoDTO);
        }
        return result;
    }

    private Programador getJefeById(String id) throws SQLException {
        ProgramadorService service = new ProgramadorService(new ProgramadorRepository());
        return service.getById(id);
    }


    public ProyectoDTO getProyectoById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id));
    }

    public ProyectoDTO postProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto res = this.save(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(res);
    }

    public ProyectoDTO updateProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto res = this.update(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(res);
    }

    public ProyectoDTO deleteProyecto(ProyectoDTO proyectoDTO) throws SQLException {
        Proyecto res = this.delete(mapper.fromDTO(proyectoDTO));
        return mapper.toDTO(res);
    }


    public Set<Proyecto> getAllProyectosByProgramador(String id) throws SQLException {
        ProyectoRepository repo = new ProyectoRepository();

        return repo.getByProgramador(id);
    }


    public Set<Proyecto> getProyectosByDepartment(String nombreDepartamento) throws SQLException {
        ProyectoRepository repo = new ProyectoRepository();

        return repo.getByDepartment(nombreDepartamento);
    }





}

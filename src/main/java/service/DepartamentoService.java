package service;

import dto.DepartamentoDTO;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import mapper.DepartamentoMapper;
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

public class DepartamentoService extends BaseService<Departamento, String, DepartamentoRepository >{
    DepartamentoMapper mapper = new DepartamentoMapper();


    public DepartamentoService(DepartamentoRepository repository) {
        super(repository);
    }


    public List<DepartamentoDTO> getAllDepartamentos() throws SQLException {

        List<Departamento> departamentos = this.findAll();
        List<DepartamentoDTO> result = new ArrayList<>();

        // Ahora debemos añadir al DTO un Programador (jefedep) y tres sets( proy.terminados, proy.desarrollo, historicoJefes)

        for (Departamento departamento : departamentos) {
            DepartamentoDTO departamentoDTO = mapper.toDTO(departamento);
            departamentoDTO.setJefeDepartamento(this.getjefeDepById(departamento.getIdJefeDepartamento()));

            //Cargamos las listas
            //De momento le estoy metiendo todos los proyectos, tengo que filtrar por terminados o no terminados.
            departamentoDTO.setProyectosTerminados(getDepartamentoProyectos(departamentoDTO.getNombre()));
            departamentoDTO.setProyectosDesarrollo(getDepartamentoProyectos(departamentoDTO.getNombre()));
            departamentoDTO.setHistoricoJefes(getDepartamentoJefes(departamentoDTO.getNombre()));
            result.add(departamentoDTO);

        }
        return result;
    }

    public Set<Programador> getDepartamentoJefes(String nombreDepartamento) throws SQLException {

        ProgramadorService service = new ProgramadorService(new ProgramadorRepository());
        return service.getJefesByDepartment(nombreDepartamento);
    }

    public Programador getjefeDepById(String id) throws SQLException {
        ProgramadorService service = new ProgramadorService(new ProgramadorRepository());
        return service.getById(id);
    }

    public Set<Proyecto> getDepartamentoProyectos(String nombreDepartamento) throws SQLException {
        ProyectoService service = new ProyectoService(new ProyectoRepository());
        return service.getProyectosByDepartment(nombreDepartamento);
    }


}


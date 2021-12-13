import dto.DepartamentoDTO;
import model.Departamento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.DepartamentoRepository;
import service.DepartamentoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartamentoTest {

    Departamento department=new Departamento("5", "Finanzas", "53ece7ae-3ed0-49c0-b50d-0db04f50d367", 1200.00, 40000.00);
    DepartamentoRepository departamento = new DepartamentoRepository();
    DepartamentoService departamentoS = new DepartamentoService(departamento);

    @Test
    void addDepartment() throws SQLException {
        Departamento depar = departamentoS.save(department);
        Assertions.assertEquals(department.getId(),depar.getId());
    }

    @Test
    void findAllDepartments() throws SQLException {
        List<DepartamentoDTO> depar = departamentoS.getAllDepartamentos();
        Assertions.assertEquals(4, depar.size());
    }

    @Test
    void getDepartmentById() throws SQLException {
        Departamento depar = departamentoS.getById("5");
        Assertions.assertEquals(depar.getId(),department.getId());
    }

    @Test
    void deleteDepartment() throws SQLException {
        Departamento depar = departamentoS.delete(department);
        Assertions.assertEquals(depar, department);
    }
}

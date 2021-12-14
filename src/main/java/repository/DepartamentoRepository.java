package repository;

import database.DataBaseController;
import model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartamentoRepository implements CrudRepository<Departamento, String> {

    @Override
    public List<Departamento> findAll() throws SQLException {
        String query = "SELECT *  FROM departamento";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error DepartamentoRepository al consultar registros de Departamentos"));
        ArrayList<Departamento> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Departamento(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getString("jefeActual"),
                            result.getDouble("presupuesto"),
                            result.getDouble("presupuestoAnual")
                    )
            );
        }
        db.close();
        return list;
    }


    @Override
    public Departamento getById(String ID) throws SQLException {
        String query = "SELECT * FROM departamento WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error PostRepository al consultar post con ID " + ID));
        if (result.first()) {
            Departamento departamento = new Departamento(
                    result.getString("id"),
                    result.getString("nombre"),
                    result.getString("jefeActual"),
                    result.getDouble("presupuesto"),
                    result.getDouble("presupuestoAnual")

            );
            db.close();
            return departamento;
        } else
            throw new SQLException("Error ProgramadorProyectoRepository no existe ProgramadorProyecto con ID: " + ID);

    }

    @Override
    public Departamento save(Departamento departamento) throws SQLException {
        String query = "INSERT INTO departamento VALUES (?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, departamento.getId().toString(), departamento.getNombre(), departamento.getIdJefeDepartamento().toString(),
                        departamento.getPresupuesto(), departamento.getPresupuestoAnual())
                .orElseThrow(() -> new SQLException("Error DepartamentoRepository al insertar Departamento"));

            departamento.setId(result.getString(1));
            db.close();
            return departamento;
    }


    @Override
    public Departamento update(Departamento departamento) throws SQLException {
        String query = "UPDATE departamento SET nombre = ?, jefeActual = ?, presupuesto = ?,  " +
                "presupuestoAnual = ? WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, departamento.getNombre(), departamento.getIdJefeDepartamento().toString(),
                departamento.getPresupuesto(), departamento.getPresupuestoAnual(), departamento.getId().toString());
        db.close();
        if (res > 0)
            return departamento;
        else
            throw new SQLException("Error DepartamentoRepository al actualizar departamento con id: " + departamento.getId().toString());
    }

    @Override
    public Departamento delete(Departamento departamento) throws SQLException {
        String query = "DELETE FROM departamento WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, departamento.getId().toString());
        db.close();
        if (res > 0)
            return departamento;
        else
            throw new SQLException("Error DepartamentoRepository al eliminar departamento con id: " + departamento.getId().toString());
    }








    //Aqui crear sentencias como Todos los proyectos de un departamento
}

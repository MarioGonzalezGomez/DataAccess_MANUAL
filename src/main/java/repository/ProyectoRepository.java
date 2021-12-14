package repository;

import database.DataBaseController;
import model.Programador;
import model.Proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProyectoRepository implements CrudRepository<Proyecto, String> {
    @Override
    public List<Proyecto> findAll() throws SQLException {
        String query = "SELECT *  FROM proyecto";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error ProyectoRepository al consultar registros de Proyectos"));
        ArrayList<Proyecto> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Proyecto(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getString("jefeProyecto"),
                            result.getDouble("presupuesto"),
                            result.getDate("fechaInicio"),
                            result.getDate("fechaFin")
                    )
            );
        }
        db.close();
        return list;

    }
    @Override
    public Proyecto getById(String ID) throws SQLException {
        String query = "SELECT * FROM proyecto WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, ID).orElseThrow(() -> new SQLException("Error ProyectoRepository al consultar proyecto con ID " + ID));
        if (result.first()) {
            Proyecto proyecto = new Proyecto(
                    result.getString("id"),
                    result.getString("nombre"),
                    result.getString("jefeProyecto"),
                    result.getDouble("presupuesto"),
                    result.getDate("fechaInicio"),
                    result.getDate("fechaFin")
            );
            db.close();
            return proyecto;
        } else
            throw new SQLException("Error ProyectoRepository no existe proyecto con ID: " + ID);
    }

    @Override
    public Proyecto save(Proyecto proyecto) throws SQLException {
        String query = "INSERT INTO proyecto VALUES (?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, proyecto.getIdProyecto(), proyecto.getNombre(), proyecto.getIdjefeProyecto(), proyecto.getPresupuesto()
                , proyecto.getFechaInicio(), proyecto.getFechaFin()).orElseThrow(() ->
                new SQLException("Error ProyectoRepository al insertar proyecto"));

            proyecto.setIdProyecto(result.getString(1));
            db.close();
            return proyecto;
        }

    @Override
    public Proyecto update(Proyecto proyecto) throws SQLException {
        String query = "UPDATE proyecto SET nombre = ?, jefeProyecto = ?, presupuesto = ?,  " +
                "fechaInicio = ?, fechaFin = ? WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, proyecto.getNombre(), proyecto.getIdjefeProyecto().toString(), proyecto.getPresupuesto()
                , proyecto.getFechaInicio(), proyecto.getFechaFin(), proyecto.getIdProyecto().toString());
        db.close();
        if (res > 0)
            return proyecto;
        else
            throw new SQLException("Error ProgramadorRepository al actualizar programador con id: " + proyecto.getIdProyecto().toString());
    }

    @Override
    public Proyecto delete(Proyecto proyecto) throws SQLException {
        String query = "DELETE FROM proyecto WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, proyecto.getIdProyecto().toString());
        db.close();
        if (res > 0)
            return proyecto;
        else
            throw new SQLException("Error ProgramadorRepository al eliminar programador con id: " + proyecto.getIdProyecto().toString());
    }







    public Set<Proyecto> getByDepartment(String nombreDepartamento) throws SQLException {
        String query = "select *from proyecto as pr \n" +
                "inner join programador as pro\n" +
                "on pr.jefeProyecto =  pro.id\n" +
                "inner join departamento as dep\n" +
                "on pro.departamento = dep.id\n" +
                "where dep.nombre = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, nombreDepartamento).orElseThrow(() -> new SQLException("Error proyectoRepository no existen proyectos en " + nombreDepartamento));
        Set<Proyecto> list = new HashSet<>();
        while (result.next()) {
            list.add(
                    new Proyecto(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getString("jefeProyecto"),
                            result.getDouble("presupuesto"),
                            result.getDate("fechaInicio"),
                            result.getDate("fechaFin")
                    )
            );
        }
        db.close();
        return list;
    }


    public Set<Proyecto> getByProgramador(String idProgramador) throws SQLException {
        String query = "SELECT * from proyecto p \n" +
                "inner join programadores_proyectos pp\n" +
                "on p.id = pp.proyectoid\n" +
                "inner join programador pro\n" +
                "on pp.programadorid=pro.id\n" +
                "where pro.id= ?;";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, idProgramador).orElseThrow(() -> new SQLException("Error CommentRepository no existe comentario con idPost " + idProgramador));
        Set<Proyecto> list = new HashSet<>();
        while (result.next()) {
            list.add(
                    new Proyecto(
                            result.getString("id"),
                            result.getString("nombre"),
                            result.getString("jefeProyecto"),
                            result.getDouble("presupuesto"),
                            result.getDate("fechaInicio"),
                            result.getDate("fechaFin")
                    )
            );
        }
        db.close();
        return list;
    }



}

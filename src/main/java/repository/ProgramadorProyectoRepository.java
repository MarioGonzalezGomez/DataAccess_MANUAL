package repository;

import database.DataBaseController;
import model.ProgramadorProyecto;
import model.Proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProgramadorProyectoRepository implements CrudRepository<ProgramadorProyecto, String> {
    @Override
    public List<ProgramadorProyecto> findAll() throws SQLException {
        String query = "SELECT *  FROM programador_proyecto";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error ProgramadorProyectoRepository al consultar registros de ProgramadorProyecto"));
        ArrayList<ProgramadorProyecto> list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new ProgramadorProyecto(
                            UUID.fromString(result.getString("id")),
                            UUID.fromString(result.getString("programador")),
                            UUID.fromString(result.getString("proyecto"))
                    )
            );
        }
        db.close();
        return list;
    }

    @Override
    public ProgramadorProyecto getById(String d) throws SQLException {
        String query = "SELECT * FROM programador_proyecto WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.select(query, d).orElseThrow(() -> new SQLException("Error ProgramadorProyectoRepository al consultar ProgramadorProyecto con ID " + d));
        if (result.first()) {
            ProgramadorProyecto pp = new ProgramadorProyecto(
                    UUID.fromString(result.getString("id")),
                    UUID.fromString(result.getString("programador")),
                    UUID.fromString(result.getString("proyecto"))
            );
            db.close();
            return pp;
        } else
            throw new SQLException("Error ProgramadorProyectoRepository no existe ProgramadorProyecto con ID: " + d);
    }

    @Override
    public ProgramadorProyecto save(ProgramadorProyecto programadorRepository) throws SQLException {
        String query = "INSERT INTO programador_proyecto VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        ResultSet result = db.insert(query, programadorRepository.getIdProgamadorProyecto().toString(), programadorRepository.getIdProgramador().toString(), programadorRepository.getIdProyecto().toString()).orElseThrow(() ->
                new SQLException("Error ProgramadorProyectoRepository al insertar proyecto"));
        if (result.first()) {
            programadorRepository.setIdProyecto(UUID.fromString(result.getString(1)));

            db.close();
            return programadorRepository;
        } else
            throw new SQLException("Error ProgramadorProyectoRepository al insertar ProgramadorProyecto en BBDD");
    }

    @Override
    public ProgramadorProyecto update(ProgramadorProyecto programadorRepository) throws SQLException {
        String query = "UPDATE proyecto SET id = ?, programador = ? , proyecto = ?  WHERE id = ?";

        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.update(query, programadorRepository.getIdProgramador().toString(), programadorRepository.getIdProyecto().toString(), programadorRepository.getIdProgamadorProyecto().toString());
        db.close();
        if (res > 0)
            return programadorRepository;
        else
            throw new SQLException("Error ProgramadorProyectoRepository al actualizar ProgramadorProyecto con id: " + programadorRepository.getIdProyecto().toString());
    }

    @Override
    public ProgramadorProyecto delete(ProgramadorProyecto programadorRepository) throws SQLException {
        String query = "DELETE FROM programador_proyecto WHERE id = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        int res = db.delete(query, programadorRepository.getIdProyecto().toString());
        db.close();
        if (res > 0)
            return programadorRepository;
        else
            throw new SQLException("Error ProgramadorProyectoRepository al eliminar ProgramadorProyecto con id: " + programadorRepository.getIdProyecto().toString());
    }
}

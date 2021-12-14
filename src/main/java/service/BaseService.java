package service;

import lombok.RequiredArgsConstructor;
import model.Proyecto;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor // Requerimos un constructor con al menos las propiedades finales
public abstract class BaseService<T, String, R extends CrudRepository<T, String>> {
    protected final R repository;

    // Operaciones CRUD

    // Obtiene todos
    public List<T> findAll() throws SQLException {
        return repository.findAll();
    }

    // Obtiene por ID
    public T getById(String d) throws SQLException {
        return repository.getById(d);
    }

    // Salva
    public T save(T t) throws SQLException {
        return repository.save(t);
    }

    // Actualiza
    public T update(T t) throws SQLException {
        return repository.update(t);
    }

    // Elimina
    public T delete(T t) throws SQLException {
        return repository.delete(t);
    }


}

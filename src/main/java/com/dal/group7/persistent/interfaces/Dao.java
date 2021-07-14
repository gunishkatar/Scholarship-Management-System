package com.dal.group7.persistent.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.EMPTY_MAP;
import static java.util.Collections.emptyList;

public abstract class Dao<K, T> {

    public Optional<T> get(K id) throws SQLException {
        return Optional.empty();
    }

    public List<T> getAll() throws SQLException {
        return emptyList();
    }

    public void insertOne(T t) throws SQLException {
    }

    public Map<K, T> getValue() throws SQLException {
        return EMPTY_MAP;
    }

    public void getByEmail() throws SQLException {
    }

    public Boolean doesExist(String id) throws SQLException {
        return false;
    }
}

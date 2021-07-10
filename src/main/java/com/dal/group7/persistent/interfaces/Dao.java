package com.dal.group7.persistent.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.*;

public abstract class Dao<K, T> {

    Optional<T> get(K id) throws SQLException {
        return Optional.empty();
    }

    List<T> getAll() throws SQLException {
        return emptyList();
    }

    void insertOne(T t) throws SQLException {
    }

    Map<K,T> getValue() throws  SQLException {
        return EMPTY_MAP;
    }
}

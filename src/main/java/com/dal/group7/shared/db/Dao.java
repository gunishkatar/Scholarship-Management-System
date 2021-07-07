package com.dal.group7.shared.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    Optional<T> get(K id) throws SQLException;

    List<T> getAll() throws SQLException;
}

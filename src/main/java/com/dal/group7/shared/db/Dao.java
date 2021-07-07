package com.dal.group7.shared.db;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    Optional<T> get(K id);

    List<T> getAll();
}

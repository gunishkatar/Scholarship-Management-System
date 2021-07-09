package com.dal.group7.constants;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SQLConstants {
    private static final String SELECT_ALL_QUERY = "select * from ";
    private static final String WHERE_ID = " where id = ?";

    private SQLConstants() {
    }

    public static String getSelectAllQuery(String table) {
        return SELECT_ALL_QUERY + table;
    }

    public static String getSelectByIdQuery(String table) {
        return SELECT_ALL_QUERY + table + WHERE_ID;
    }

    public static String insertIntoTableAllFields(String table, Integer numberOfFields) {
        final String params = IntStream.range(0, numberOfFields).mapToObj(i -> "?").collect(Collectors.joining(","));
        return "insert into " + table + " values(" + params + ");";
    }
}

package db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/20.
 */
public class Query {
    public StringBuffer queryString;
    public Connection connection;
    boolean hasWhere, hasOrder;

    public Query(String tableName, Connection connection) {
        queryString = new StringBuffer("select * from " + tableName);
        this.connection = connection;
        hasWhere = false;
        hasOrder = false;
    }

    public Query(String tableName) throws Exception {
        this(tableName, Database.getConnection());
    }

    public Query where(String fieldName, char operator, String value) {
        if (!hasWhere) {
            hasWhere = true;
            queryString.append(" where ");
        }
        queryString.append(fieldName);
        queryString.append(operator);
        queryString.append("\'" + value + "\'");
        return this;
    }

    public Query and() {
        queryString.append(" and ");
        return this;
    }

    public Query or() {
        queryString.append(" or ");
        return this;
    }

    public Query where(String fieldName, char operator, int value) {
        if (!hasWhere) {
            hasWhere = true;
            queryString.append(" where ");
        }
        queryString.append(fieldName);
        queryString.append(operator);
        queryString.append(value);
        return this;
    }

    public Query orderBy(String fieldName, String direction) {
        if (!hasOrder) {
            hasOrder = true;
            queryString.append(" order by ");
        } else {
            queryString.append(",");
        }
        queryString.append(fieldName + " " + direction);
        return this;
    }

    public ResultSet executeQuery() throws Exception {
        System.out.println(queryString);
        return connection.createStatement().executeQuery(queryString.toString());
    }
}

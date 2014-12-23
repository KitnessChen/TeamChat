package db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/20.
 */
public class Query {
    public StringBuffer queryString;
    public Connection connection;
    boolean hasWhere, hasOrder, hasFrom;

    public Query(Connection connection) {
        queryString = new StringBuffer("select * ");
        this.connection = connection;
        hasWhere = false;
        hasOrder = false;
        hasFrom = false;
    }

    public Query() throws Exception {
        this(Database.getConnection());
    }

    public Query from(String tableName) {
        if (!hasFrom) {
            queryString.append(" from ");
            hasFrom = true;
        } else {
            queryString.append(" , ");
        }
        queryString.append(tableName);
        return this;
    }

    public Query join(String fieldName1, String operator, String fieldName2) {
        if (!hasWhere) {
            hasWhere = true;
            queryString.append(" where ");
        }
        queryString.append(fieldName1);
        queryString.append(operator);
        queryString.append(fieldName2);
        return this;
    }

    public Query where(String fieldName, String operator, String value) {
        if (!hasWhere) {
            hasWhere = true;
            queryString.append(" where ");
        }
        queryString.append(fieldName);
        queryString.append(operator);
        queryString.append("\'" + value + "\'");
        return this;
    }

    public Query where(String fieldName, String operator, int value) {
        if (!hasWhere) {
            hasWhere = true;
            queryString.append(" where ");
        }
        queryString.append(fieldName);
        queryString.append(operator);
        queryString.append(value);
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

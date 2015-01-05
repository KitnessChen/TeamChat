package dbobject;

import db.Database;

import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by whd on 2014/12/20.
 */
public class BaseDatabaseObject {

    private String defaultTableName;
    private Connection connection;
    public int id;

    public BaseDatabaseObject(String defaultTableName) throws Exception {
        this.defaultTableName = defaultTableName;
        connection = Database.getConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void remove() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from " + defaultTableName + " where id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public void update() throws Exception {
        Field fields[] = this.getClass().getFields();
        ArrayList<Field> fieldList = new ArrayList<Field>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("id")) continue;
            fieldList.add(fields[i]);
        }

        StringBuffer fieldString = new StringBuffer("");
        for (int i = 0; i < fieldList.size(); i++) {
            if (i > 0)
                fieldString.append(",");
            fieldString.append(fieldList.get(i).getName() + " = ? ");
        }

        PreparedStatement statement = connection.prepareStatement
                ("update set " + fieldString + " where id = ?");
        for (int i = 0; i < fieldList.size(); i++) {
            if (fieldList.get(i).getType().equals(int.class)) {
                statement.setInt(i + 1, (Integer) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(String.class)) {
                statement.setString(i + 1, (String) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(Date.class)) {
                statement.setDate(i + 1, (Date) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(Time.class)) {
                statement.setTime(i + 1, (Time) fieldList.get(i).get(this));
            }
        }
        statement.setInt(fieldList.size() + 1, this.id);
        System.out.println("update set " + fieldString + " where id = ?");
        statement.executeUpdate();
    }

    public void insert(String tableName) throws SQLException, UnknownHostException, IllegalAccessException {
        Field fields[] = this.getClass().getFields();
        ArrayList<Field> fieldList = new ArrayList<Field>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("id")) continue;
            fieldList.add(fields[i]);
        }

        StringBuffer fieldString = new StringBuffer("");
        for (int i = 0; i < fieldList.size(); i++) {
            if (i > 0)
                fieldString.append(",");
            fieldString.append(fieldList.get(i).getName());
        }

        StringBuffer unknownMarkString = new StringBuffer("");
        for (int i = 0; i < fieldList.size(); i++) {
            if (i > 0)
                unknownMarkString.append(",");
            unknownMarkString.append("?");
        }
        PreparedStatement statement = connection.prepareStatement
                ("insert into " + tableName + "(" + fieldString + ")" + " values " + " (" + unknownMarkString + ") ");
        for (int i = 0; i < fieldList.size(); i++) {
            if (fieldList.get(i).getType().equals(int.class)) {
                statement.setInt(i + 1, (Integer) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(String.class)) {
                statement.setString(i + 1, (String) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(Date.class)) {
                statement.setDate(i + 1, (Date) fieldList.get(i).get(this));
            } else if (fieldList.get(i).getType().equals(Time.class)) {
                statement.setTime(i + 1, (Time) fieldList.get(i).get(this));
            }
        }
        System.out.println("insert into " + tableName + "(" + fieldString + ")" + " values " + " (" + unknownMarkString + ") ");
        statement.executeUpdate();
    }

    public void insert() throws SQLException, UnknownHostException, IllegalAccessException {
        System.out.println(defaultTableName);
        insert(defaultTableName);
    }
}

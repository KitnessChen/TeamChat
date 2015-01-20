package dbobject;

import db.Database;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by whd on 2014/12/20.
 * <p>对于这个项目里面的所有数据库对象（一个数据库对象指的是数据库里面的一行）的一个封装，所有数据库对象都以这个类为基类</p>
 * <p>这个类对于数据库里面的若干常用方法：插入/删除/更新/从ResultSet转化过来/转化为JSON 等功能都有实现</p>
 * <p>使用这个类之后，大部分对于数据库对象的操作都可以用类内的方法而不是用手写sql语句来完成</p>
 * <p>实现的方法主要是用java的反射这个特性</p>
 * <p>每个数据库实例都有自增id这个域</p>
 */
public class BaseDatabaseObject {

    private String defaultTableName;
    private Connection connection;
    public int id;

    /**
     * 指定该数据库对象的默认表，如果不特殊指定的话所有操作都是对这个表进行操作
     *
     * @param defaultTableName 指定这个数据库对象的默认表
     * @throws Exception
     */
    public BaseDatabaseObject(String defaultTableName) throws Exception {
        this.defaultTableName = defaultTableName;
        connection = Database.getConnection();
    }

    /**
     * <p>返回这个数据库操作所使用的connection</p>
     * <p>有时候为了线程安全，某些操作需要在同一个connection下依次执行，所以提供了这个方法</p>
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 在表中删除这个数据库对象的实例，仅通过这个实例的id来在表中找到这个实例
     *
     * @throws SQLException
     */
    public void remove() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("delete from " + defaultTableName + " where id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    /**
     * 更新表中的数据库对象实例的方法，仅通过这个实例的id来在表中找到这个实例
     *
     * @throws Exception
     */
    public void update() throws Exception {
        Field fields[] = this.getClass().getFields();
        ArrayList<Field> fieldList = new ArrayList<Field>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("id")) continue;
            fieldList.add(fields[i]);
        }

        StringBuilder fieldString = new StringBuilder("");
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

    /**
     * 将ResultSet转化为数据库对象实例，保存在this中
     *
     * @param resultSet 要转化的ResultSet
     * @return 转化成的数据库对象实例
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public BaseDatabaseObject fromResultSet(ResultSet resultSet) throws SQLException, IllegalAccessException {
        Field fields[] = this.getClass().getFields();
        for (Field field : fields) {
            if (field.getType().equals(int.class)) {
                field.set(this, resultSet.getInt(field.getName()));
            } else if (field.getType().equals(String.class)) {
                field.set(this, resultSet.getString(field.getName()));
            } else if (field.getType().equals(Date.class)) {
                field.set(this, resultSet.getDate(field.getName()));
            } else if (field.getType().equals(Time.class)) {
                field.set(this, resultSet.getTime(field.getName()));
            }
        }
        return this;
    }

    /**
     * 把这个实例转化成JSON对象，这里的JSON对象是用的开源库org.json里面的JSONObject
     *
     * @return 转化成的JSONObject
     * @throws Exception
     */
    public JSONObject toJSONObject() throws Exception {
        JSONObject jsonObject = new JSONObject();
        Field fields[] = this.getClass().getFields();
        for (Field field : fields) {
            jsonObject.put(field.getName().toLowerCase(), field.get(this));
        }
        return jsonObject;
    }

    /**
     * 把这个实例插入到某个表里面
     *
     * @param tableName 实例要插入到的表里面
     * @throws SQLException
     * @throws UnknownHostException
     * @throws IllegalAccessException
     */
    public void insert(String tableName) throws SQLException, UnknownHostException, IllegalAccessException {
        Field fields[] = this.getClass().getFields();
        ArrayList<Field> fieldList = new ArrayList<Field>();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals("id")) continue;
            fieldList.add(fields[i]);
        }

        StringBuilder fieldString = new StringBuilder("");
        for (int i = 0; i < fieldList.size(); i++) {
            if (i > 0)
                fieldString.append(",");
            fieldString.append(fieldList.get(i).getName());
        }

        StringBuilder unknownMarkString = new StringBuilder("");
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

    /**
     * 把数据库实例插入到这个数据库实例的默认表里面
     *
     * @throws SQLException
     * @throws UnknownHostException
     * @throws IllegalAccessException
     */
    public void insert() throws SQLException, UnknownHostException, IllegalAccessException {
        System.out.println(defaultTableName);
        insert(defaultTableName);
    }
}

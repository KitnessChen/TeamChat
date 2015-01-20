package db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/20.
 * <p>Query类，通过它的几个方法来构造sql查询语句并执行，目的是尽量减少代码中的纯sql语句。</p>
 * <p>直接在代码中写sql的话感觉不容易直观地看出它的结构，用这个类主要是为了让代码看起来清晰一点。</p>
 * <p>实现方法其实很简单，就是拼接几个词来产生完整的sql语句，然后用数据库接口运行查询。</p>
 * <p>支持链式调用，调用的时候要严格按照sql原本应当有的顺序使用那几个关键词</p>
 */
public class Query {
    public StringBuffer queryString;
    public Connection connection;
    boolean hasWhere, hasOrder, hasFrom;

    /**
     *
     * @param connection 为了线程安全，用已有的connection初始化Query
     */
    public Query(Connection connection) {
        queryString = new StringBuffer("select * ");
        this.connection = connection;
        hasWhere = false;
        hasOrder = false;
        hasFrom = false;
    }

    /**
     * 取一个新的connection来初始化Query
     * @throws Exception
     */
    public Query() throws Exception {
        this(Database.getConnection());
    }

    /**
     * 相当于sql语句里面的from table
     * @param tableName from tableName
     * @return 添加from部分之后的Query
     */
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

    /**
     * 按某个条件对两个表连接，条件为A.x operator B.y
     * @param fieldName1 A.x A为一个表的名字，x为条件中A表要比较的域
     * @param operator 用来比较的运算符，比如>,=,<等等
     * @param fieldName2 B.y B为一个表的名字，y为条件中B表要比较的域
     * @return 添加join部分之后的Query
     */
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

    /**
     * where条件从句,比较的值为String
     * @param fieldName 与条件有关的域的名字
     * @param operator 比较运算符
     * @param value 比较的值
     * @return 添加where部分之后的Query
     */
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
    
    /**
     * where条件从句,比较的值为int
     * @param fieldName 与条件有关的域的名字
     * @param operator 比较运算符
     * @param value 比较的值
     * @return 添加where部分之后的Query
     */
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

    /**
     * 添加一个and
     * @return 添加and部分之后的Query
     */
    public Query and() {
        queryString.append(" and ");
        return this;
    }
    /**
     * 添加一个or
     * @return 添加or部分之后的Query
     */
    public Query or() {
        queryString.append(" or ");
        return this;
    }

    /**
     * 添加order by从句
     * @param fieldName 要排序的域的名字
     * @param direction 排序的方向: asc/desc
     * @return 添加order by部分之后的Query
     */
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

    /**
     * 添加top从句,相当于mysql里面的limit
     * @param top top x里面的x
     * @return 添加了top从句之后的Query
     */
    public Query top(int top) {
        queryString.delete(queryString.length() - 3, queryString.length());
        queryString.append(" top " + top + " * ");
        return this;
    }

    /**
     * 执行当前拼接出来的sql查询语句，返回执行结果ResultSet
     * @return ResultSet，查询结果
     * @throws Exception
     */
    public ResultSet executeQuery() throws Exception {
        System.out.println(queryString);
        return connection.createStatement().executeQuery(queryString.toString());
    }
}

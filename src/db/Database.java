package db;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/1.
 */
public class Database {

    public static Connection getConnection() throws UnknownHostException, SQLException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String classUri = Database.class.getResource("").getPath().substring(1);
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=" + classUri + "../../../Database/AccessDB.mdb";
        return DriverManager.getConnection(url, "", "");
    }
}

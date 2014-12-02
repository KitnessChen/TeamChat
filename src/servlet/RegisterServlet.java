package servlet;

import db.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by whd on 2014/11/30.
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO get parameters

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("aaaaa");
        out.close();
//
//        User user = new User();
//        user.setNickname("whd");
//        user.setWorkPosition("student");

        Connection connection = null;
        try {
            connection = Database.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into Users(nickname, workPosition) values('whd', 'student')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

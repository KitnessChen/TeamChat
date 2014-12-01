package servlet;

import db.Database;
import dbobject.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by whd on 2014/11/30.
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO get parameters

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setId("rrrl8523690");
        user.setNickname("whd");
        user.setWorkPosition("student");

        Database.getCollection("Users").insert(user);
    }
}

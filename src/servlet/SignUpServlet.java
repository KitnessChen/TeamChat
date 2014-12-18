package servlet;

import db.Database;
import dbobject.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whd on 2014/11/30.
 */
public class SignupServlet extends BaseServlet {

    public SignupServlet() {
        super("/pages/front_end/signup.jsp");
    }

    protected void signupAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));

        System.out.println(user.getUserName() + " " + user.getPassword());

        try {
            Connection connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from Users where UserName = ? ");
            statement.setString(1, request.getParameter("username"));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                response.getWriter().write("username is already used");
            } else {
                statement = connection.prepareStatement
                        ("insert into Users(UserName, Password, Email) values(?, ?, ?)");
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.executeUpdate();
                response.getWriter().write("register succeeded");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

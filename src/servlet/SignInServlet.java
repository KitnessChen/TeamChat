package servlet;

import db.Database;
import dbobject.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/11/30.
 */

//TODO T^T
public class SigninServlet extends BaseServlet {

    public SigninServlet() {
        super("/pages/front_end/signin.jsp");
    }

    public void signinAction(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.userName = request.getParameter("username");
        user.password = request.getParameter("password");

        Connection connection = null;
        try {
            connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select UserName, Password, ID from Users where UserName = ? ");
            statement.setString(1, user.userName);
            ResultSet resultSet = statement.executeQuery();

            HttpSession session = request.getSession(true);
            if (null != session.getAttribute("UserName")) {
                response.getWriter().write("already logged in");
            } else if (!resultSet.next() ||
                    !resultSet.getString("Password").equals(user.password)) {
                response.getWriter().write("log in failed");
            } else {
                user.id = resultSet.getString("ID");
                session.setAttribute("username", user.userName);
                session.setAttribute("userid", user.id);
                response.getWriter().write("log in succeeded");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

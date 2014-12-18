package servlet;

import db.Database;
import dbobject.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whd on 2014/11/30.
 */

//TODO T^T
public class SignInServlet extends BaseServlet {

    public SignInServlet() {
        super("/pages/front_end/signin.jsp");
    }

    public void signInAction(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setUserName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        Connection connection = null;
        try {
            connection = Database.getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("select UserName, Password, ID from Users where UserName = ? ");
            statement.setString(1, user.getUserName());
            ResultSet resultSet = statement.executeQuery();

            HttpSession session = request.getSession(true);
            if (null != session.getAttribute("UserName")) {
                response.getWriter().write("already logged in");
            } else if (!resultSet.next() ||
                    !resultSet.getString("Password").equals(user.getPassword())) {
                response.getWriter().write("log in failed");
            } else {
                user.setId(resultSet.getString("ID"));
                session.setAttribute("username", user.getUserName());
                session.setAttribute("userid", user.getId());
                response.getWriter().write("log in succeeded");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

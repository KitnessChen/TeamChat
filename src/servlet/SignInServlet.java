package servlet;

import db.Query;
import dbobject.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/11/30.
 */

public class SigninServlet extends BaseServlet {

    public SigninServlet() {
        super("/pages/front_end/signin.jsp");
    }

    public void signinAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        user.userName = request.getParameter("username");
        user.password = request.getParameter("password");

        Connection connection = null;
        try {
            ResultSet resultSet = new Query()
                    .from("Users")
                    .where("UserName", "=", user.userName)
                    .executeQuery();

            HttpSession session = request.getSession(true);
            if (null != session.getAttribute("UserName")) {
                response.getWriter().write("already logged in");
            } else if (!resultSet.next() ||
                    !resultSet.getString("Password").equals(user.password)) {
                response.getWriter().write("log in failed");
            } else {
                user.id = resultSet.getInt("Id");
                session.setAttribute("username", user.userName);
                session.setAttribute("userid", user.id);
                response.getWriter().write("log in succeeded");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

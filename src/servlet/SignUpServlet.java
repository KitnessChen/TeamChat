package servlet;

import db.Query;
import dbobject.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whd on 2014/11/30.
 */
public class SignupServlet extends BaseServlet {

    public SignupServlet() {
        super("/pages/front_end/signup.jsp");
    }

    protected void signupAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        user.userName = request.getParameter("username");
        user.password = request.getParameter("password");
        user.email = request.getParameter("email");

//        System.out.println(user.getUserName() + " " + user.getPassword());

        try {
            ResultSet resultSet = new Query()
                    .from("Users")
                    .where("UserName", "=", request.getParameter("username"))
                    .executeQuery();
            if (resultSet.next()) {
                response.getWriter().write("username is already used");
            } else {
                user.insert();
                response.getWriter().write("register succeeded");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

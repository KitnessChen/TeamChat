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

    /**
     * 显示的页面
     */
    public SigninServlet() {
        super("/pages/front_end/signin.jsp");
    }

    /**
     * 完成登录的功能，并且设置session里面的username和userid
     *
     * @param request  <ol>
     *                 <li>需要参数username</li>
     *                 <li>需要参数password</li>
     *                 </ol>
     * @param response
     * @throws Exception
     */
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
            if (null != session.getAttribute("username")) {
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

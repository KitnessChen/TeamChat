package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by whd on 2014/11/30.
 */

public class SignoutServlet extends BaseServlet {
    /**
     * 完成登出功能，即去除session里面的username和userid
     *
     * @param request
     * @param response
     */
    public void signoutAction(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (null != session) {
            session.removeAttribute("username");
            session.removeAttribute("userid");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }
}

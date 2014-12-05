package servlet;

import dbobject.PrivateMessage;
import dbobject.PublicMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/4.
 */
public class MessageServlet extends RedirectServlet {
    public MessageServlet() {
        super("/pages/front_end/messages.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        String userName = request.getSession().getAttribute("username").toString();
        String userId = request.getSession().getAttribute("userid").toString();
        if (null == userName || null == userId) {
            response.getWriter().write("please log in");
            return;
        }

        if (request.getParameter("type").equals("public message")) {
            PublicMessage message = new PublicMessage();
            message.setContent(request.getParameter("content").toString());
            message.setFromUserId(Integer.parseInt(userId));
            message.setPublishTime(new Date(System.currentTimeMillis()));
            message.setTeamId(Integer.parseInt(request.getParameter("teamid").toString()));
            try {
                message.insert();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("type").equals("private message")) {
            PrivateMessage message = new PrivateMessage();
            message.setContent(request.getParameter("content").toString());
            message.setFromUserId(Integer.parseInt(userId));
            message.setPublishTime(new Date(System.currentTimeMillis()));
            message.setTeamId(Integer.parseInt(request.getParameter("teamid").toString()));
            message.setToUserId(Integer.parseInt(request.getParameter("touserid").toString()));
            try {
                message.insert();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package servlet;

import dbobject.PublicMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Created by whd on 2014/12/4.
 */
public class MessageServlet extends BaseServlet {
    public MessageServlet() {
        super("/pages/front_end/messages.jsp");
    }

    public void sendPublicMessageAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String userName = request.getSession().getAttribute("username").toString();
        String userId = request.getSession().getAttribute("userid").toString();

        if (null == userName || null == userId) {
            response.getWriter().write("please log in");
            return;
        }

        String tableName = "TeamMessage" + request.getParameter("teamid");
        PublicMessage message = new PublicMessage(tableName);
        message.content = request.getParameter("content").toString();
        message.fromUserId = Integer.parseInt(userId);
        message.publishDate = new Date(System.currentTimeMillis());
        message.publishTime = new Time(System.currentTimeMillis());
        try {
            message.insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendPrivateMessageAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String userName = request.getSession().getAttribute("username").toString();
        String userId = request.getSession().getAttribute("userid").toString();

        if (null == userName || null == userId) {
            response.getWriter().write("please log in");
            return;
        }

        String tableName = "TeamMessage" + request.getParameter("teamid");
        PublicMessage message = new PublicMessage(tableName);
        message.content = request.getParameter("content").toString();
        message.fromUserId = Integer.parseInt(userId);
        message.publishDate = new Date(System.currentTimeMillis());
        message.publishTime = new Time(System.currentTimeMillis());
        message.toUserId = Integer.parseInt(request.getParameter("touserid").toString());
        try {
            message.insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

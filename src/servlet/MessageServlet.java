package servlet;

import dbobject.PrivateMessage;
import dbobject.PublicMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by whd on 2014/12/4.
 */
public class MessageServlet extends RedirectServlet {
    public MessageServlet() {
        super("/pages/front_end/messages.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        System.out.println("get post");
        String userName = request.getSession().getAttribute("username").toString();
        String userId = request.getSession().getAttribute("userid").toString();
        if (null == userName || null == userId) {
            System.out.println("!!!");
            response.getWriter().write("please log in");
            return;
        }
        System.out.println("!!" + request.getParameter("teamid"));
        Enumeration tmp = request.getParameterNames();
        while (tmp.hasMoreElements()) {
            System.out.println(request.getParameter(tmp.nextElement().toString()));
        }
        if (request.getParameter("query").equals("public message")) {
            System.out.println("####");
            PublicMessage message = new PublicMessage();
            message.setContent(request.getParameter("content").toString());
            message.setFromUserId(Integer.parseInt(userId));
            message.setPublishTime(new SimpleDateFormat("mm月 dd日 HH:mm:ss").format(new Date()));
            message.setTeamId(Integer.parseInt(request.getParameter("teamid").toString()));
            try {
                message.insert();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (request.getParameter("query").equals("private message")) {
            PrivateMessage message = new PrivateMessage();
            message.setContent(request.getParameter("content").toString());
            message.setFromUserId(Integer.parseInt(userId));
            message.setPublishTime(new SimpleDateFormat("MM月 dd日 HH:mm:ss").format(new Date()));
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

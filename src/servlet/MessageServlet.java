package servlet;

import db.Query;
import db.Validation;
import dbobject.Message;
import dbobject.PrivateMessage;
import dbobject.PublicMessage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.ResultSet;
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
        int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int teamId = Integer.parseInt(request.getParameter("teamid"));

        if (null == userName || 0 == userId || !Validation.checkUserInTeam(userId, teamId)) {
            response.getWriter().write("failed");
            return;
        }

        String tableName = "TeamMessage" + teamId;
        PublicMessage message = new PublicMessage(tableName);
        message.content = request.getParameter("content").toString();
        message.fromUserId = userId;
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
        int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int toUserId = Integer.parseInt(request.getParameter("touserid"));

        if (null == userName || 0 == userId || !Validation.checkUserInTeam(userId, teamId) || !Validation.checkUserInTeam(toUserId, teamId)) {
            response.getWriter().write("failed");
            return;
        }


        String tableName = "TeamMessage" + request.getParameter("teamid");
        PrivateMessage message = new PrivateMessage(tableName);
        message.content = request.getParameter("content").toString();
        message.fromUserId = userId;
        message.publishDate = new Date(System.currentTimeMillis());
        message.publishTime = new Time(System.currentTimeMillis());
        message.toUserId = toUserId;
        try {
            message.insert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getMessageListAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int toUserId = Integer.parseInt(request.getParameter("touserid"));
        int lastMessageId = Integer.parseInt(request.getParameter("lastmessageid"));

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String tableName = "TeamMessage" + teamId;

        if (Validation.checkUserInTeam(userId, teamId)) {
            ResultSet resultSet =
                    new Query()
                            .from(tableName)
                            .where("id", ">", lastMessageId).and().where("ToUserId", "=", toUserId)
                            .orderBy("PublishDate", "asc").orderBy("PublishTime", "asc")
                            .executeQuery();
            while (resultSet.next()) {
                JSONObject jsonMessage = new Message(tableName).fromResultSet(resultSet).toJSONObject();
                array.put(jsonMessage);
            }
            result.put("status", "ok");
        } else
            result.put("status", "error");
        result.put("messageList", array);
        response.getWriter().write(result.toString());
    }

}

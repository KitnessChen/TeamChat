package servlet;

import db.Query;
import db.Validation;
import dbobject.Message;
import dbobject.PrivateMessage;
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


    /**
     * 发送信息
     *
     * @param request  <ol>
     *                 <li>需要以队伍内的一个人的身份登录</li>
     *                 <li>需要参数teamid</li>
     *                 <li>需要参数touserid，当touserid=-1时代表队伍内可见的信息</li>
     *                 <li>把内容放在content域里面</li>
     *                 </ol>
     * @param response
     * @throws Exception
     */
    public void sendMessageAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String userName = request.getSession().getAttribute("username").toString();
        int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int toUserId = Integer.parseInt(request.getParameter("touserid"));

        if (null == userName || 0 == userId || !Validation.checkUserInTeam(userId, teamId)
                || (toUserId != -1 && !Validation.checkUserInTeam(toUserId, teamId))) {
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

    /**
     * <pre>
     * 得到某队伍里面的某条信息之后的所有信息
     * </pre>
     *
     * @param request  <ol>
     *                 <li>需要以队伍内的人的身份登录</li>
     *                 <li>需要参数touserid，当touserid=-1时代表请求公开聊天信息，touserid!=-1时代表请求userid与touserid之间私聊的信息</li>
     *                 <li>需要参数lastmessageid，代表请求在这条信息之后出现的信息</li>
     *                 </ol>
     * @param response <p>请求返回的数据格式</p>
     *                 <p>{messageList:[{id:, fromuserid:, touserid:, contenttype:, content}..., ], status:"ok"/"error"}</p>
     * @throws Exception
     */
    public void getMessageListAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int toUserId = Integer.parseInt(request.getParameter("touserid"));
        int lastMessageId = Integer.parseInt(request.getParameter("lastmessageid"));

        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        String tableName = "TeamMessage" + teamId;

        if (-1 == toUserId) {
            if (Validation.checkUserInTeam(userId, teamId)) {
                ResultSet resultSet =
                        new Query()
                                .from(tableName)
                                .where("id", ">", lastMessageId).and().where("ToUserId", "=", toUserId)
                                .orderBy("id", "asc")
                                .executeQuery();
                while (resultSet.next()) {
                    JSONObject jsonMessage = new Message(tableName).fromResultSet(resultSet).toJSONObject();
                    array.put(jsonMessage);
                }
                result.put("status", "ok");
            } else
                result.put("status", "error");
        } else {
            if (Validation.checkUserInTeam(userId, teamId) && Validation.checkUserInTeam(toUserId, teamId)) {
                String tmp = " and ((FromUserId = " + userId + " and ToUserId = " + toUserId + ") or " +
                        "(FromUserId = " + toUserId + " and ToUserId = " + userId + "))";
                ResultSet resultSet =
                        new Query()
                                .from(tableName)
                                .where("id", ">", lastMessageId).and().where("ToUserId", "=", toUserId)
                                .str(tmp)
                                .orderBy("id", "asc")
                                .executeQuery();
                while (resultSet.next()) {
                    JSONObject jsonMessage = new Message(tableName).fromResultSet(resultSet).toJSONObject();
                    array.put(jsonMessage);
                }
                result.put("status", "ok");
            } else {
                result.put("status", "error");
            }
        }
        result.put("messageList", array);
        response.getWriter().write(result.toString());
    }

}

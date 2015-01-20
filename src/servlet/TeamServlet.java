package servlet;

import db.Database;
import db.Query;
import db.Validation;
import dbobject.Team;
import dbobject.TeamUser;
import dbobject.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/4
 * <p/>
 * 主要作用是提供若干与Team相关的Action
 * .
 */
public class TeamServlet extends BaseServlet {

    public TeamServlet() {
        super("/pages/front_end/team.jsp");
    }

    /**
     * 由team创建者向team添加一个成员，包含一些必要的检查
     *
     * @param request  <ol>
     *                 <li> 需要以team创建者身份登录</li>
     *                 <li>包含userid</li>
     *                 <li>包含teamid</li>
     *                 </ol>
     * @param response 写出添加成员操作的返回结果（一句话）
     */
    public void addTeamMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("userid"));
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int thisUserId = (Integer) request.getSession().getAttribute("userid");
        if (Validation.checkUserInTeam(userId, teamId)) {
            response.getWriter().write("already in team");
        } else if (Validation.isTeamCreator(thisUserId, teamId)) {
            TeamUser teamUser = new TeamUser();
            teamUser.userId = userId;
            teamUser.teamId = teamId;
            teamUser.insert();

            response.getWriter().write("team member added successfully");
        } else {
            response.getWriter().write("you don't have permission to add a member");
        }
    }

    /**
     * 从某个队伍移除某个成员，包含一些必要的检查
     *
     * @param request  <ol>
     *                 <li> 需要以team创建者身份登录</li>
     *                 <li>包含userid</li>
     *                 <li>包含teamid</li>
     *                 </ol>
     * @param response 写出删除成员操作的返回结果（一句话）
     */
    public void removeTeamMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userId = Integer.parseInt(request.getParameter("userid"));
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        int thisUserId = (Integer) request.getSession().getAttribute("userid");
        if (Validation.checkUserInTeam(userId, teamId)) {
            response.getWriter().write("not in team");
        } else if (Validation.isTeamCreator(thisUserId, teamId)) {
            TeamUser teamUser = new TeamUser();
            ResultSet resultSet = new Query()
                    .from("Team_User")
                    .where("UserId", "=", userId)
                    .and()
                    .where("TeamId", "=", teamId)
                    .executeQuery();
            teamUser.fromResultSet(resultSet);
            teamUser.remove();
            response.getWriter().write("team member removed successfully");
        } else {
            response.getWriter().write("you don't have permission to add a member");
        }
    }

    /**
     * 创建一个队伍，包含一些必要的检查
     *
     * @param request  <ol>
     *                 <li>需要登录</li>
     *                 <li>包含teamname</li>
     *                 </ol>
     * @param response 写出创建队伍的结果（一句话）
     * @throws Exception
     */
    public void createTeamAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userId = (Integer) request.getSession().getAttribute("userid");
        String teamName = request.getParameter("teamname");


        Team team = new Team();
        team.creatorId = userId;
        team.teamName = teamName;

        ResultSet resultSet = new Query(team.getConnection())
                .from("Teams")
                .where("CreatorId", "=", userId)
                .and()
                .where("TeamName", "=", teamName)
                .executeQuery();
        if (resultSet.next()) {
            response.getWriter().write("failed");
            return;
        }

        team.insert();

        // insert into teams table
        resultSet = new Query(team.getConnection())
                .from("Teams")
                .where("CreatorId", "=", userId)
                .and()
                .where("TeamName", "=", teamName)
                .executeQuery();
        System.out.println(resultSet);
        resultSet.next();
        String teamId = Integer.toString(resultSet.getInt("Id"));
        //create teammessage table
        String sqlString = "create table TeamMessage" + teamId +
                "(Id counter, FromUserId int,ToUserId int, PublishDate date, PublishTime time, ContentType text(20), Content text(255))";
        Database.getConnection().createStatement().executeUpdate(sqlString);

        response.getWriter().write("team created successfully");

    }

    /**
     * 得到某个队伍的所有的成员的信息
     *
     * @param request  包含teamid
     * @param response 写出一个json，格式如下（变量名全小写）
     *                 {
     *                 memberlist:
     *                 [
     *                 {id:int, username:string, ...},
     *                 ...
     *                 ]
     *                 }
     */
    public void getTeamMemberListAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultSet resultSet = new Query()
                .from("Users")
                .from("Team_User")
                .join("Users.Id", "=", "Team_User.UserId")
                .and()
                .where("Team_User.TeamId", "=", Integer.parseInt(request.getParameter("teamid")))
                .executeQuery();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        int cnt = 0;
        while (resultSet.next()) {
//            JSONObject json = new JSONObject();
//            json.put("userid", resultSet.getInt(1));
//            json.put("username", resultSet.getString(2));
//            array.put(json);
            array.put(new User().fromResultSet(resultSet).toJSONObject());
        }
        result.put("memberlist", array);
        System.out.println(result.toString());
        response.getWriter().write(result.toString());
    }

    /**
     * 得到某个人所在的所有队伍的列表
     *
     * @param request  包含userid
     * @param response 写出一个json，格式如下（变量名全小写）
     *                 {
     *                 teamlist:
     *                 [
     *                 {id:int, teamname:string, ...},
     *                 ...
     *                 ]
     *                 }
     */
    public void getTeamListAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultSet resultSet = new Query()
                .from("Teams")
                .from("Team_User")
                .join("Teams.Id", "=", "Team_User.TeamId")
                .and()
                .where("Team_User.UserId", "=", Integer.parseInt(request.getParameter("userid")))
                .executeQuery();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();

        while (resultSet.next()) {
//            JSONObject json = new JSONObject();
//            json.put("teamid", resultSet.getInt("TeamId"));
//            json.put("teamname", resultSet.getString("TeamName"));
//            array.put(json);
            array.put(new Team().fromResultSet(resultSet).toJSONObject());
        }
        result.put("teamlist", array);
        System.out.println(result.toString());
        response.getWriter().write(result.toString());
    }

    public void searchTeamAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}

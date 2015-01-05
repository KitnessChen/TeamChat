package servlet;

import db.Database;
import db.Query;
import db.Validation;
import dbobject.Team;
import dbobject.TeamUser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/4.
 */
public class TeamServlet extends BaseServlet {

    public TeamServlet() {
        super("/pages/front_end/team.jsp");
    }

    public void addTeamMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //TODO check if the person has the right to let another person in

        int userId = Integer.parseInt(request.getParameter("userid"));
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        if (Validation.checkUserInTeam(userId, teamId)) {
            response.getWriter().write("already in team");
        } else {
            TeamUser teamUser = new TeamUser();
            teamUser.userId = userId;
            teamUser.teamId = teamId;
            teamUser.insert();

            response.getWriter().write("team member added successfully");
        }
    }

    public void removeTeamMemberAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //TODO check if the person has the right to let another person in

        int userId = Integer.parseInt(request.getParameter("userid"));
        int teamId = Integer.parseInt(request.getParameter("teamid"));
        if (!Validation.checkUserInTeam(userId, teamId)) {
            response.getWriter().write("not in team");
        } else {
            TeamUser teamUser = new TeamUser();
            teamUser.userId = userId;
            teamUser.teamId = teamId;
            response.getWriter().write("team member removed successfully");
        }
    }

    public void createTeamAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userId = (Integer) request.getSession().getAttribute("userid");
        String teamName = request.getParameter("teamname");

        Team team = new Team();
        team.creatorId = userId;
        team.teamName = teamName;
        team.insert();

        // insert into teams table
        ResultSet resultSet = new Query(team.getConnection())
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
        System.out.println("1");
        while (resultSet.next()) {
            System.out.println("2");
            JSONObject json = new JSONObject();
            json.put("userid", resultSet.getInt(1));
            json.put("username", resultSet.getString(2));
            array.put(json);
        }
        System.out.println("3");
        result.put("memberList", array);
        response.getWriter().write(result.toString());
    }

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
            JSONObject json = new JSONObject();
            json.put("teamid", resultSet.getInt("TeamId"));
            json.put("teamname", resultSet.getString("TeamName"));
            array.put(json);
        }
        result.put("teamList", array);
        response.getWriter().write(result.toString());
    }
}

package servlet;

import db.Database;
import db.Validation;
import dbobject.Team;
import dbobject.TeamUser;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/4.
 */
public class TeamServlet extends BaseServlet {

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

    public void createTeamAction(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int userId = (Integer) request.getSession().getAttribute("userid");
        String teamName = request.getParameter("teamname");

        Team team = new Team();
        team.creatorId = userId;
        team.teamName = teamName;
        team.insert();

        Connection connection = team.getConnection();
        // insert into teams table
        PreparedStatement statement = connection.prepareStatement
                ("select Id from Teams where CreatorId = ? and TeamName = ?");
        //get teamid of the recently created team
        statement.setInt(1, userId);
        statement.setString(2, teamName);
        ResultSet resultSet = statement.executeQuery();
        System.out.println(resultSet);
        resultSet.next();
        String teamId = Integer.toString(resultSet.getInt("Id"));
        //create teammessage table
        String sqlString = "create table TeamMessage" + teamId +
                "(Id counter, FromUserId int,ToUserId int, PublishDate date, PublishTime time, ContentType text(20), Content text(255))";
        connection.createStatement().executeUpdate(sqlString);

        response.getWriter().write("team created successfully");

    }

    public void getTeamMemberList(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("select Team_User.UserId, Users.UserName from Users, Team_User where " +
                        "Users.Id = Team_User.UserId and Team_User.TeamId = ?");
        statement.setInt(1, Integer.parseInt(request.getParameter("teamid")));
        ResultSet resultSet = statement.executeQuery();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        while (resultSet.next()) {
            JSONObject json = new JSONObject();
            json.put("userid", resultSet.getInt(1));
            json.put("username", resultSet.getString(2));
            array.put(json);
        }
        result.put("memberList", array);
        response.getWriter().write(result.toString());
    }

    public void getTeamList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Connection connection = null;
        connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("select Team_User.TeamId, Teams.TeamName from Teams, Team_User where " +
                        "Teams.Id = Team_User.TeamId and Team_User.UserId = ?");
        statement.setInt(1, Integer.parseInt(request.getParameter("userid")));
        ResultSet resultSet = statement.executeQuery();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        while (resultSet.next()) {
            JSONObject json = new JSONObject();
            json.put("teamid", resultSet.getInt(1));
            json.put("teamname", resultSet.getString(2));
            array.put(json);
        }
        result.put("teamList", array);
        response.getWriter().write(result.toString());
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("type").equals("get team members")) {
            } else if (request.getParameter("type").equals("get one\'s teams")) {
            }
        } catch (Exception e) {

        }
    }
}

package servlet;

import db.Database;
import db.Validation;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
public class TeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");

            if (request.getParameter("type").equals("add team member")) {
                //TODO check if the person has the right to let another person in
                String userId = request.getParameter("userid");
                String teamId = request.getParameter("teamid");
                if (Validation.checkUserInTeam(userId, teamId)) {
                    response.getWriter().write("already in team");
                } else {
                    Connection connection = Database.getConnection();
                    PreparedStatement statement = connection.prepareStatement
                            ("insert into Team_User (TeamId, UserId) values(?, ?)");
                    statement.setString(1, teamId);
                    statement.setString(2, userId);
                    statement.executeUpdate();
                    response.getWriter().write("team member added successfully");
                }
            } else if (request.getParameter("type").equals("create team")) {
                String userId = request.getSession().getAttribute("userid").toString();
                String teamName = request.getParameter("teamname");

                Connection connection = Database.getConnection();
                // insert into teams table
                PreparedStatement statement = connection.prepareStatement
                        ("insert into Teams (TeamName, CreatorId) values(?, ?)");
                statement.setString(1, teamName);
                statement.setString(2, userId);
                statement.executeUpdate();
                statement = connection.prepareStatement
                        ("select ID from Teams where CreatorId = ? and TeamName = ?");
                //get teamid of the recently created team
                statement.setInt(1, Integer.parseInt(userId));
                statement.setString(2, teamName);
                ResultSet resultSet = statement.executeQuery();
                resultSet.next();
                String teamId = Integer.toString(resultSet.getInt("ID"));
                String sqlString = "create table TeamMessage" + teamId + "(FromUserId int, ToUserId int, PublishTime date, Content text(255))";
                connection.createStatement().executeUpdate(sqlString);
                response.getWriter().write("team created successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            if (request.getParameter("type").equals("get team members")) {
                Connection connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement
                        ("select Team_User.UserId, Users.UserName from Users, Team_User where " +
                                "Users.ID = Team_User.UserId and Team_User.TeamId = ?");
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
            } else if (request.getParameter("type").equals("get one\'s teams")) {
                Connection connection = null;
                connection = Database.getConnection();
                PreparedStatement statement = connection.prepareStatement
                        ("select Team_User.TeamId, Teams.TeamName from Teams, Team_User where " +
                                "Teams.ID = Team_User.TeamId and Team_User.UserId = ?");
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
        } catch (Exception e) {

        }
    }
}

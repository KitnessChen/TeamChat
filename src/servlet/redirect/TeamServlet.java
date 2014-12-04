package servlet.redirect;

import db.Database;
import db.Validation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/2.
 */
public class TeamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        System.out.println("1");
        if (request.getParameter("type").equals("add team member")) {
            try {
                System.out.println("2");
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
            } catch (SQLException e) {

            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

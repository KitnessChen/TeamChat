package db;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/4.
 */
public class Validation {
    public static boolean checkUserInTeam(String userId, String teamId) throws SQLException, UnknownHostException {
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement
                ("select * from Team_User where UserId = ? and TeamId = ?");
        statement.setString(1, userId);
        statement.setString(2, teamId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return true;
        return false;
    }
}

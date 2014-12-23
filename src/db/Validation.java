package db;

import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/4.
 */
public class Validation {
    public static boolean checkUserInTeam(int userId, int teamId) throws Exception {
        ResultSet resultSet = new Query()
                .from("Team_User")
                .where("UserId", "=", userId)
                .and()
                .where("TeamId", "=", teamId)
                .executeQuery();
        if (resultSet.next())
            return true;
        return false;
    }
}

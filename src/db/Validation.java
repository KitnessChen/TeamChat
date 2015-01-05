package db;

import dbobject.User;

import java.sql.ResultSet;

/**
 * Created by whd on 2014/12/4.
 */
public class Validation {

    public static boolean validateUser(User user) {
        String invalidChar = " +-/*?!@#$%^&*()<>[]{}\'\";,.:";
        if (user.userName.length() <= 3)
            return false;
        if (user.password.length() <= 6)
            return false;
        for (int i = 0; i < user.userName.length(); i++)
            for (int j = 0; j < invalidChar.length(); j++)
                if (user.userName.charAt(i) == invalidChar.charAt(j))
                    return false;
        for (int i = 0; i < user.password.length(); i++)
            for (int j = 0; j < invalidChar.length(); j++)
                if (user.password.charAt(i) == invalidChar.charAt(j))
                    return false;
        return true;
    }

    public static boolean isTeamCreator(int userId, int teamId) throws Exception {
        ResultSet resultSet = new Query()
                .from("Teams")
                .where("Creator", "=", userId)
                .and()
                .where("TeamId", "=", teamId)
                .executeQuery();
        if (resultSet.next())
            return true;
        return false;
    }

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

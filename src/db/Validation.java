package db;

import dbobject.User;

import java.sql.ResultSet;

/**
 * <pre>
 * Created by whd on 2014/12/4.
 * 用来做若干与合法性相关的判断的类
 * </pre>
 */
public class Validation {

    /**
     * 用来判断用户的名字和密码是否合法
     * @param user 传入一个用户实例
     * @return 这个用户的名字和密码是否合法
     */
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

    /**
     * 判断某个人是否是某个队伍的创建者
     * @param userId 要判断的人的id
     * @param teamId 要判断的队伍的id
     * @return 是否这个人是这个队伍的创建者
     * @throws Exception
     */
    public static boolean isTeamCreator(int userId, int teamId) throws Exception {
        ResultSet resultSet = new Query()
                .from("Teams")
                .where("CreatorId", "=", userId)
                .and()
                .where("Id", "=", teamId)
                .executeQuery();
        if (resultSet.next())
            return true;
        return false;
    }

    /**
     * 判断某个人是否在某个队伍里面
     * @param userId 要判断的人的id
     * @param teamId 要判断的队伍的id
     * @return 这个人是否在这个队伍里面
     * @throws Exception
     */
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

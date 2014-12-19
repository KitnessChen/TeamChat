package dbobject;

import db.Database;

import java.net.UnknownHostException;
import java.sql.*;

/**
 * Created by whd on 2014/12/1.
 */
//TODO complete
public class Message {

    public Date publishDate;
    public Time publishTime;
    public String type;
    public String content;
    public int teamId;
    public int fromUserId;
    public int toUserId;

    public void insert() throws SQLException, UnknownHostException {
        Connection connection = Database.getConnection();
        String sqlString = "insert into TeamMessage" + teamId + "(FromUserId, ToUserId, PublishDate, PublishTime, Content) " +
                "values(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlString);
        statement.setInt(1, fromUserId);
        statement.setInt(2, toUserId);
        statement.setDate(3, publishDate);
        statement.setTime(4, publishTime);
        statement.setString(5, content);
        statement.executeUpdate();
    }
}

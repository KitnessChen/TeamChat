package dbobject;

import db.Database;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/1.
 */
//TODO complete
public class Message {
    private Date publishTime;
    private String content;
    private int teamId;
    private int fromUserId;
    private int toUserId;

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public void insert() throws SQLException, UnknownHostException {
        Connection connection = Database.getConnection();
        String sqlString = "insert into TeamMessage" + teamId + "(FromUserId, ToUserId, PublishTime, Content) " +
                "values(?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sqlString);
        statement.setInt(1, fromUserId);
        statement.setInt(2, toUserId);
        statement.setDate(3, publishTime);
        statement.setString(4, content);
        statement.executeUpdate();
    }
}

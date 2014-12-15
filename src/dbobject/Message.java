package dbobject;

import db.Database;

import java.net.UnknownHostException;
import java.sql.*;

/**
 * Created by whd on 2014/12/1.
 */
//TODO complete
public class Message {

    private Date publishDate;
    private Time publishTime;
    private String content;
    private int teamId;
    private int fromUserId;
    private int toUserId;

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublishTime(Time publishTime) {
        this.publishTime = publishTime;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
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

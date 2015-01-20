package dbobject;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by whd on 2014/12/1.
 * <pre>队伍聊天信息的数据库对象</pre>
 */
public class Message extends BaseDatabaseObject {

    public Date publishDate;
    public Time publishTime;
    public String contentType = "";
    public String content = "";
    public int fromUserId;
    public int toUserId;

    public Message(String defaultTableName) throws Exception {
        super(defaultTableName);
    }

    //    public void insert() throws SQLException, UnknownHostException {
//        Connection connection = Database.getConnection();
//        String sqlString = "insert into TeamMessage" + teamId + "(FromUserId, ToUserId, PublishDate, PublishTime, Content) " +
//                "values(?, ?, ?, ?, ?)";
//        PreparedStatement statement = connection.prepareStatement(sqlString);
//        statement.setInt(1, fromUserId);
//        statement.setInt(2, toUserId);
//        statement.setDate(3, publishDate);
//        statement.setTime(4, publishTime);
//        statement.setString(5, content);
//        statement.executeUpdate();
//    }
}

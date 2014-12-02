package dbobject;

import db.Database;

import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by whd on 2014/12/1.
 */
// id, name
//TODO complete
public class Team {
    private String teamName;
    private int creatorId;

    public Team(String teamName, int creatorId) {
        this.teamName = teamName;
        this.creatorId = creatorId;
    }

    public void insert() throws SQLException, UnknownHostException {
        PreparedStatement query = Database.getConnection().
                prepareStatement("insert into Teams(teamName, creatorId) values(?, ?)");
        query.setString(1, teamName);
        query.setInt(2, creatorId);
        query.executeUpdate();
        System.out.println("#####");
    }
}

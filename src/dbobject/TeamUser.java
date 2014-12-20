package dbobject;

/**
 * Created by whd on 2014/12/20.
 */
public class TeamUser extends BaseDatabaseObject {
    public int teamId;
    public int userId;

    public TeamUser() throws Exception {
        super("Team_User");
    }
}

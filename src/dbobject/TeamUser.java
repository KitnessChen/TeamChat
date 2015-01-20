package dbobject;

/**
 * Created by whd on 2014/12/20.
 * <pre>存储队伍和成员的关系的数据库对象，如果表中有这么一行就说明这个人在这个队伍里面</pre>
 */
public class TeamUser extends BaseDatabaseObject {
    public int teamId;
    public int userId;

    /**
     * 由于这个类所有的实例都是存储在Team_User表里面的，所以提供了默认构造函数
     *
     * @throws Exception
     */
    public TeamUser() throws Exception {
        super("Team_User");
    }
}

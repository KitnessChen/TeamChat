package dbobject;

/**
 * Created by whd on 2014/12/1.
 * <pre>队伍的数据库对象</pre>
 */
public class Team extends BaseDatabaseObject {
    public String teamName = "";
    public int creatorId;
    public String description = "";

    /**
     * 所有Team都是存储在Teams表里面的，所以这里提供了默认构造函数
     *
     * @throws Exception
     */
    public Team() throws Exception {
        super("Teams");
    }

}

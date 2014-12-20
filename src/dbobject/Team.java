package dbobject;

/**
 * Created by whd on 2014/12/1.
 */
// id, name
//TODO complete
public class Team extends BaseDatabaseObject {
    public String teamName;
    public int creatorId;

    public Team() throws Exception {
        super("Teams");
    }

}

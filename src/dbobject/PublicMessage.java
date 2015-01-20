package dbobject;

/**
 * Created by whd on 2014/12/1.
 * <pre>队伍聊天的数据库对象，用toUserId = -1来体现出这个信息是队伍内公开的</pre>
 */
public class PublicMessage extends Message {
    public PublicMessage(String defaultTableName) throws Exception {
        super(defaultTableName);
        toUserId = -1;
    }
}

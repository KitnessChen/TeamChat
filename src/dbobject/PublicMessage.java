package dbobject;

/**
 * Created by whd on 2014/12/1.
 */
public class PublicMessage extends Message {
    public PublicMessage(String defaultTableName) {
        super(defaultTableName);
        toUserId = -1;
    }
}

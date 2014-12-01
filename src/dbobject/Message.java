package dbobject;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by whd on 2014/12/1.
 */
//TODO complete
public class Message extends BasicDBObject {
    private Date publishTime;
    private String content;
    private ObjectId fromUser;
}

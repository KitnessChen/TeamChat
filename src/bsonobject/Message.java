package bsonobject;

import com.mongodb.DBCollection;
import com.mongodb.DBRef;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by whd on 2014/12/1.
 */
//TODO complete
public class Message extends BasicBSONObject {
    private Date publishTime;
    private String content;
    private ObjectId fromUser;
}

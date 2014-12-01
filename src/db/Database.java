package db;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by whd on 2014/12/1.
 */
public class Database {

    public static DBCollection getCollection(String collectionName) throws UnknownHostException {
        return new MongoClient("http://localhost", 27017).getDB("TeamWork").getCollection(collectionName);
    }
}

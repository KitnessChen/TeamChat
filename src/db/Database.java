package db;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

import java.net.UnknownHostException;

/**
 * Created by whd on 2014/12/1.
 */
public class Database {

    public static DBCollection getCollection(String collectionName) throws UnknownHostException {
        MongoClient client = new MongoClient("localhost", 27017);
        client.setWriteConcern(WriteConcern.NORMAL);
        return client.getDB("TeamWork").getCollection(collectionName);
    }
}

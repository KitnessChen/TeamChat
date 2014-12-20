package dbobject;

import java.sql.Date;

/**
 * Created by whd on 2014/11/30.
 */

/*
*
* mainly for register
*
*
* */
public class User extends BaseDatabaseObject {
    public int id;
    public String userName;
    public String password;
    public Date birthday;
    public String workPosition;
    public String location;
    public String phoneNumber;
    public String websiteURL;
    public String email;


    public User() throws Exception {
        super("Users");
    }
}

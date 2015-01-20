package dbobject;

import java.sql.Date;

/**
 * Created by whd on 2014/11/30.
 * <pre>用户的数据库对象</pre>
 */
public class User extends BaseDatabaseObject {
    public String userName = "";
    public String password = "";
    public Date birthday;
    public String workPosition = "";
    public String location = "";
    public String phoneNumber = "";
    public String websiteURL = "";
    public String email = "";

    /**
     * 由于所有的用户都是存储在Users表里面的，所以提供了默认构造函数
     *
     * @throws Exception
     */
    public User() throws Exception {
        super("Users");
    }
}

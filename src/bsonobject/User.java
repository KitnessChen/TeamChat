package bsonobject;

import org.bson.BasicBSONObject;

/**
 * Created by whd on 2014/11/30.
 */
public class User extends BasicBSONObject {
    private String id;
    private String nickname;
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.put("id", id);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.put("nickname", nickname);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.put("password", password);
    }


    public User(String id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }
}

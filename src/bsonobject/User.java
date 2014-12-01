package bsonobject;

import org.bson.BasicBSONObject;

import java.util.Date;

/**
 * Created by whd on 2014/11/30.
 */
public class User extends BasicBSONObject {
    private String id;
    private String nickname;
    private String password;
    private Date birthday;
    private String workPosition;
    private String location;
    private String phoneNumber;
    private String websiteURL;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.put("birthday", birthday);
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
        this.put("workPosition", workPosition);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.put("location", location);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.put("phoneNumber", phoneNumber);
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
        this.put("websiteURL", websiteURL);
    }
}

package cn.ecnuer996.volunteer.entity;

import java.util.Date;

/**
 * @author 11135
 */
public class Comment {
    private String volunteerId;
    private String avatar;
    private String nickName;
    private String content;
    private Date date;

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package cn.ecnuer996.volunteer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author LuoChengLing
 */
@Document(value = "volunteer")
public class Volunteer {
    @Id
    private String _id;
    private String openid;
    private String avatar;
    private String nickname;
    private String name;
    private String school;
    private Double integral;
    private Integer frequency;
    private Double serviceTime;
    private List<String> favoriteOrg;
    private List<String> favoriteActivity;
    private List<Record> records;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Double getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public List<String> getFavoriteOrg() {
        return favoriteOrg;
    }

    public void setFavoriteOrg(List<String> favoriteOrg) {
        this.favoriteOrg = favoriteOrg;
    }

    public List<String> getFavoriteActivity() {
        return favoriteActivity;
    }

    public void setFavoriteActivity(List<String> favoriteActivity) {
        this.favoriteActivity = favoriteActivity;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}

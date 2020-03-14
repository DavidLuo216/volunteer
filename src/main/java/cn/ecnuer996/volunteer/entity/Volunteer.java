package cn.ecnuer996.volunteer.entity;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoChengLing
 */
@Document(value = "volunteer")
public class Volunteer {

    @Id
    private String id;
    private String openid;
    private String sessionKey;
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
    private String phone;
    private String schoolId;

    public Boolean isRegistered(String activityId) {
        List<Record> records = this.records;
        if (records == null) {
            return false;
        }
        for (Record record : records) {
            return record.getActivityId().equals(activityId) && !record.getState().equals("已取消");
        }
        return false;
    }

    public Volunteer() {
        this.favoriteOrg = new ArrayList<String>();
        this.favoriteActivity = new ArrayList<String>();
        this.records = new ArrayList<Record>();
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
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

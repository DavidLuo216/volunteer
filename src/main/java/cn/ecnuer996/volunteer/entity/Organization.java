package cn.ecnuer996.volunteer.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author 11135
 */
@Document(collection = "organization")
public class Organization {
    @Id
    private String id;
    private String name;
    private String icon;
    private String address;
    private String introduction;
    private Double score;
    private Integer favoriteNum;
    private List<String> essays;
    private List<String> activities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(Integer favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public List<String> getEssays() {
        return essays;
    }

    public void setEssays(List<String> essays) {
        this.essays = essays;
    }

    public List<String> getActivities() {
        return activities;
    }

    public void setActivities(List<String> activities) {
        this.activities = activities;
    }
}

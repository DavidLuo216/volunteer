package cn.ecnuer996.volunteer.entity;

/**
 * @author 11135
 */
public class Record implements Comparable {
    private String activityId;
    private String state;
    private String certificate;
    private Double score;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        if ("待审核".equals(this.getState()) || "已通过".equals(this.getState())) {
            return -1;
        } else {
            return 1;
        }
    }
}

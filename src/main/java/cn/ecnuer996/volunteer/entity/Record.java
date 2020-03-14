package cn.ecnuer996.volunteer.entity;

import cn.ecnuer996.volunteer.util.StateCode;

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
        if (StateCode.WAITING_APPROVE.state().equals(this.getState()) || StateCode.PASSED.state().equals(this.getState())) {
            return -1;
        } else {
            return 1;
        }
    }
}

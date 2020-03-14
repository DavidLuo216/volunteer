package cn.ecnuer996.volunteer.util;

/**
 * @author xusheng
 */
public enum StateCode {
    WAITING_APPROVE("待审核"),

    PASSED("已通过"),

    CANCELED("已取消"),

    WAITING_COMMENT("待评价"),

    COMMENTED("已评价");

    private final String state;

    StateCode(String state) {
        this.state = state;
    }

    public String state() {
        return state;
    }
}

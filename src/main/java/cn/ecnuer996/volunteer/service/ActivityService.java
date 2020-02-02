package cn.ecnuer996.volunteer.service;

import cn.ecnuer996.volunteer.entity.Activity;

import java.util.List;

/**
 * @author 11135
 */
public interface ActivityService {

    /**
     * 获取推荐活动列表
     * @param tags tags
     * @return 推荐活动列表
     */
    List<Activity> getRecommendActivities(List<String> tags);
}

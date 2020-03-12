package cn.ecnuer996.volunteer.service;

import cn.ecnuer996.volunteer.entity.Activity;
import org.bson.types.ObjectId;

import java.util.HashMap;
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
    List<Activity> listRecommendActivities(List<String> tags);

    /**
     * 通过活动id获取活动详情
     * @param activityId 活动id
     * @param userId 用户id
     * @return 活动详情+是否收藏
     */
    HashMap<String, Object> getActivityDetail(String activityId, String userId);

    /**
     * 通过组织id获取该组织的活动列表
     * @param id 组织id
     * @return 活动列表
     */
    List<Activity> listActivitiesByOrganizationId(ObjectId id);

}

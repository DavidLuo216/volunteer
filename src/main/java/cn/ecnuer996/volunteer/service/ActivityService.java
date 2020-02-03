package cn.ecnuer996.volunteer.service;

import cn.ecnuer996.volunteer.entity.Activity;
import org.bson.types.ObjectId;

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
     * 获取活动详情
     * @param id 活动id
     * @return 活动详情
     */
    Activity getActivityDetail(ObjectId id);
}

package cn.ecnuer996.volunteer.service;


import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Volunteer;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;

/**
 * @author 11135
 */
public interface VolunteerService {

    /**
     * 微信登陆
     * @param code code
     * @param nickName 用户昵称
     * @return 用户ID
     */
    String logIn(String code,String nickName);

    /**
     * 查找主键id对应用户
     * @param id id
     * @return volunteer
     */
    Volunteer getVolunteerInfo(ObjectId id);

    /**
     * 改变用户收藏某个活动的状态，若已收藏，则删去，若无，则添加
     * @param userId 用户id
     * @param activityId 活动id
     */
    void changeFavorStatus(ObjectId userId,ObjectId activityId);

    /**
     * 根据用户id查询报名过的项目详情
     * @param userId 用户ID
     * @return 用户报名过的项目和参与详情
     */
    List<HashMap> listTakenActivities(ObjectId userId);
}

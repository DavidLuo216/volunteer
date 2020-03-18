package cn.ecnuer996.volunteer.service;


import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Volunteer;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor=Exception.class)
    void changeFavorStatus(ObjectId userId,ObjectId activityId);

    /**
     * 根据用户id查询报名过的项目详情
     * @param userId 用户ID
     * @return 用户报名过的项目和参与详情
     */
    List<HashMap> listTakenActivities(ObjectId userId);

    /**
     * 用户报名活动
     * @param userId 用户ID
     * @param activityId 活动ID
     * @param info 审核说明
     */
    @Transactional(rollbackFor=Exception.class)
    void registerActivity(ObjectId userId,ObjectId activityId, String info);


    /**
     * 查询报名的详情
     * @param activityId 活动id
     * @param userId 用户id
     * @return 报名详情和活动详情
     */
    HashMap<String, Object> getTakenActivityDetail(String activityId, String userId);

    /**
     * 更新用户实名信息
     * @param userId 用户id
     * @param nickName 昵称
     * @param name 真实姓名
     * @param school 消息名称
     * @param schoolId 学号
     * @param phone 电话
     */
    void updateVolunteerInfo(ObjectId userId, String nickName,String name,String school,String schoolId,String phone);

    /**
     * 获取收藏活动列表
     * @param userId 用户id
     * @return 收藏活动列表
     */
    List<Activity> listFavoriteActivities(ObjectId userId);

    /**
     * 发表评论
     * @param userId 用户id
     * @param activityId 活动id
     * @param comment 评论内容
     */
    @Transactional(rollbackFor = Exception.class)
    void saveComment(ObjectId userId,ObjectId activityId,String comment);
}
package cn.ecnuer996.volunteer.service;


import cn.ecnuer996.volunteer.entity.Volunteer;
import org.bson.types.ObjectId;

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
}

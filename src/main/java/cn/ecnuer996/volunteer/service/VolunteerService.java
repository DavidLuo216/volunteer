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
}

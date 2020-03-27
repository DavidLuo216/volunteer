package cn.ecnuer996.volunteer.service;

import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @author xusheng
 */
public interface BackstageService {

    /**
     * 根据活动id获取申请人列表
     * @param activityId 活动id
     * @return 报名人列表
     */
    public List<HashMap> listApplicants(ObjectId activityId);

    /**
     * 通过或驳回志愿者的申请
     * @param userId 用户id
     * @param activityId 活动id
     * @param command 指令，合法值为“通过”和“拒绝”
     */
    @Transactional(rollbackFor = Exception.class)
    public void changeRegistrationStatus(ObjectId userId,ObjectId activityId,String command);

    /**
     * 组织后台登陆
     * @param name 组织名字
     * @param password 密码
     * @return 组织id
     */
    public String adminLogin(String name,String password);
}

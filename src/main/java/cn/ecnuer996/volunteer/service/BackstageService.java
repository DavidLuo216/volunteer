package cn.ecnuer996.volunteer.service;

import org.bson.types.ObjectId;

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
}

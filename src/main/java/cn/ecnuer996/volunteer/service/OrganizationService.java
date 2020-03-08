package cn.ecnuer996.volunteer.service;

import cn.ecnuer996.volunteer.entity.Organization;
import org.bson.types.ObjectId;

/**
 * @author 11135
 */
public interface OrganizationService {
    /**
     * 获取组织详情
     * @param id 组织id
     * @return 组织详情
     */
    Organization getOrganizationDetail(ObjectId id);
}

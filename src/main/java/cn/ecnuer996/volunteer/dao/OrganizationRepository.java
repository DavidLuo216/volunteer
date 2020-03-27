package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Organization;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11135
 */
public interface OrganizationRepository extends MongoRepository<Organization, ObjectId> {
    /**
     * 根据组织名字查询组织 不允许模糊查询
     * @param name 组织名字
     * @return 组织
     */
    Organization findByName(String name);
}

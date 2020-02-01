package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Organization;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11135
 */
public interface OrganizationRepository extends MongoRepository<Organization, ObjectId> {
}

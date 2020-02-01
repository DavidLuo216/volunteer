package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Activity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11135
 */
public interface ActivityRepository extends MongoRepository<Activity, ObjectId> {
}

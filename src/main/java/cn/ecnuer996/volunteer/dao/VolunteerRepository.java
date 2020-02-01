package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Volunteer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 11135
 */
@Repository
public interface VolunteerRepository extends MongoRepository<Volunteer, ObjectId> {

}

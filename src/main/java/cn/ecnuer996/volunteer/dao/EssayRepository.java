package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Essay;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 11135
 */
public interface EssayRepository extends MongoRepository<Essay, ObjectId> {
}

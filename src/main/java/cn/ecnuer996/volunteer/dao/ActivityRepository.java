package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Activity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * @author 11135
 */
public interface ActivityRepository extends MongoRepository<Activity, ObjectId> {
    /**
     * 查找包含tags的activity
     *
     * @param tags tags列表
     * @return activity列表
     */
    @Query("{tags:{$all:?0} }")
    List<Activity> findByTagsAll(List<String> tags);

    /**
     * 根据活动名称模糊查询活动
     * @param title 活动名称
     * @return 活动列表
     */
    List<Activity> findByTitleLike(String title);
}

package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.service.ActivityService;
import cn.ecnuer996.volunteer.util.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xusheng
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    private ActivityRepository activityRepository;

    @Autowired
    public void setActivityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> listRecommendActivities(List<String> tags) {
        List<Activity> activityList;
        activityList = activityRepository.findByTagsAll(tags);
        return activityList;
    }

    @Override
    public Activity getActivityDetail(ObjectId id) {
        Activity activity;
            activity=activityRepository.findById(id).orElse(null);
        return activity;
    }


}

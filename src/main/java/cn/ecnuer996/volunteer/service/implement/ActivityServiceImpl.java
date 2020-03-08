package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.OrganizationRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Organization;
import cn.ecnuer996.volunteer.service.ActivityService;
import cn.ecnuer996.volunteer.util.MongoUtil;
import cn.ecnuer996.volunteer.util.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author xusheng
 */
@Service("actService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public List<Activity> listRecommendActivities(List<String> tags) {
        List<Activity> activityList;
        activityList = activityRepository.findByTagsAll(tags);
        return activityList;
    }

    @Override
    public Activity getActivityDetail(ObjectId id) {
        Activity activity;
        activity = activityRepository.findById(id).orElse(null);
        return activity;
    }

    @Override
    public List<Activity> listActivitiesByOrganizationId(ObjectId id) {
        Organization organization = organizationRepository.findById(id).get();
        List<ObjectId> ObjectIdList = MongoUtil.toObjectIdList(organization.getActivities());
        System.out.println(ObjectIdList);
        System.out.println();
        return (List<Activity>) activityRepository.findAllById(ObjectIdList);
    }
}

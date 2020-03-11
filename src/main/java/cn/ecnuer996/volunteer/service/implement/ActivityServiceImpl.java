package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.OrganizationRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Organization;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.ActivityService;
import cn.ecnuer996.volunteer.util.MongoUtil;
import cn.ecnuer996.volunteer.util.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author xusheng
 */
@Service("actService")
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private VolunteerRepository volunteerRepository;

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
    public HashMap<String, Object> getActivityDetail(ObjectId activityId, ObjectId userId) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>(3);

        Activity activity = activityRepository.findById(activityId).get();
        resultMap.put("activityDetail", activity);
        Organization organization=organizationRepository.findById(new ObjectId(activity.getOrganizationId())).get();
        resultMap.put("organizationDetail", organization);
        Volunteer volunteer = volunteerRepository.findById(userId).get();
        List<String> favorList = volunteer.getFavoriteActivity();
        if (favorList == null) {
            resultMap.put("isFavor", false);
        } else if (favorList.contains(activityId.toString())) {
            resultMap.put("isFavor", true);
        } else {
            resultMap.put("isFavor", false);
        }
        return resultMap;
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

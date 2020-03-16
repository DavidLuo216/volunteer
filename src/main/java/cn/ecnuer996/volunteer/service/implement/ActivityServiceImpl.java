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
import java.util.ArrayList;
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
        List<Activity> resultList = new ArrayList<Activity>();
        List<Activity> activityList = activityRepository.findByTagsAll(tags);
        for (Activity activity: activityList) {
            if(activity.getState().equals("招募中")){
                resultList.add(activity);
            }
        }
        return resultList;
    }

    @Override
    public HashMap<String, Object> getActivityDetail(String activityIdS, String userIdS) {

        Activity activity = activityRepository.findById(new ObjectId(activityIdS)).get();
        Organization organization = organizationRepository.findById(new ObjectId(activity.getOrganizationId())).get();

        HashMap<String, Object> resultMap = new HashMap<String, Object>(3);
        resultMap.put("activityDetail", activity);
        resultMap.put("organizationDetail", organization);

        if (userIdS == null) {
            resultMap.put("isFavor", false);
        } else {
            Volunteer volunteer = volunteerRepository.findById(new ObjectId(userIdS)).get();
            resultMap.put("hasRegistered",volunteer.isRegistered(activityIdS));
            List<String> favorList = volunteer.getFavoriteActivity();
            if (favorList == null) {
                resultMap.put("isFavor", false);
            } else if (favorList.contains(activityIdS)) {
                resultMap.put("isFavor", true);
            } else {
                resultMap.put("isFavor", false);
            }
        }

        return resultMap;
    }

    @Override
    public HashMap<String,Object> listActivitiesByOrganizationId(ObjectId id) {
        HashMap<String,Object> resultMap=new HashMap<String, Object>(2);
        List<Activity> currentActivityList=new ArrayList<Activity>();
        List<Activity> oldActivityList=new ArrayList<Activity>();

        Organization organization = organizationRepository.findById(id).get();
        List<ObjectId> ObjectIdList = MongoUtil.toObjectIdList(organization.getActivities());
        List<Activity> allActivityList=(List<Activity>) activityRepository.findAllById(ObjectIdList);

        for (Activity activity: allActivityList) {
            if(activity.getState().equals("已结束")){
                oldActivityList.add(activity);
            }else {
                currentActivityList.add(activity);
            }
        }

        resultMap.put("currentActivityList",currentActivityList);
        resultMap.put("oldActivityList",oldActivityList);

        return resultMap;
    }

    @Override
    public List<Activity> findByTitleLike(String title) {
        return activityRepository.findByTitleLike(title);
    }
}

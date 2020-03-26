package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Applicant;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.BackstageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xusheng
 */
@Service
public class BackstageServiceImpl implements BackstageService {
    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public List<HashMap> listApplicants(ObjectId activityId) {
        List<HashMap> resultList = new ArrayList<HashMap>();
        Activity activity = activityRepository.findById(activityId).get();
        List<Applicant> applicantList = activity.getApplicants();

        for (Applicant applicant : applicantList) {
            Volunteer volunteer = volunteerRepository.findById(new ObjectId(applicant.getVolunteerId())).get();
            HashMap<String, Object> applicantMap = new HashMap<String, Object>(2);
            applicantMap.put("registrationInfo", applicant);
            applicantMap.put("applicantDetail", volunteer);
            resultList.add(applicantMap);
        }

        return resultList;
    }
}

package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.entity.Applicant;
import cn.ecnuer996.volunteer.entity.Record;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.VolunteerService;
import cn.ecnuer996.volunteer.util.AppUtil;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import cn.ecnuer996.volunteer.util.ServiceException;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 11135
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {
    private VolunteerRepository volunteerRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    public void setVolunteerRepository(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public String logIn(@NotNull String code, @NotNull String nickname) {
        String KEY_OPENID = "openid";

        // 换取用户openId
        String openid;
        String sessionKey;
        try {
            HttpRequest request = HttpRequest.get(AppUtil.WX_LOGIN_URL, true,
                    "appid", AppUtil.APP_ID, "secret", AppUtil.APP_SECRET, "js_code", code, "grant_type", "authorization_code");
            String body = request.body();
            JSONObject obj = JSONObject.parseObject(body);
            if (obj.getString(KEY_OPENID) == null) {
                throw new ServiceException("微信登陆api请求失败");
            }
            openid = obj.getString("openid");
            sessionKey = obj.getString("session_key");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("微信登陆api请求失败");
        }

        // 更新数据库用户信息
        Volunteer volunteer = volunteerRepository.findByOpenid(openid);
        if (volunteer != null) {
            // 已注册用户
            volunteer.setNickname(nickname);
            volunteer.setSessionKey(sessionKey);
            volunteerRepository.save(volunteer);
        } else {
            volunteer = new Volunteer();
            volunteer.setOpenid(openid);
            volunteer.setSessionKey(sessionKey);
            volunteer.setNickname(nickname);
            volunteerRepository.insert(volunteer);
        }
        return volunteerRepository.findByOpenid(openid).getId();
    }

    @Override
    public Volunteer getVolunteerInfo(ObjectId id) {
        Volunteer volunteer;
        try {
            volunteer = volunteerRepository.findById(id).get();
        } catch (Exception e) {
            throw new ServiceException("没有该ID对应用户");
        }
        return volunteer;
    }

    @Override
    public void changeFavorStatus(ObjectId userId, ObjectId activityId) {
        Volunteer volunteer = volunteerRepository.findById(userId).get();
        Activity activity =activityRepository.findById(activityId).get();
        List<String> favorList = volunteer.getFavoriteActivity();
        if (favorList.contains(activityId.toString())) {
            favorList.remove(activityId.toString());
            activity.setFavoriteNum(activity.getFavoriteNum()-1);
        } else {
            favorList.add(activityId.toString());
            activity.setFavoriteNum(activity.getFavoriteNum()+1);
        }
        volunteer.setFavoriteActivity(favorList);
        volunteerRepository.save(volunteer);
        activityRepository.save(activity);
    }

    @Override
    public List<HashMap> listTakenActivities(ObjectId userId) {
        Volunteer volunteer = volunteerRepository.findById(userId).get();

        List<Record> records = volunteer.getRecords();
        // 从records中取出对应的activityId
        List<ObjectId> activityIdList = new ArrayList<ObjectId>();
        for (Record record : records) {
            activityIdList.add(new ObjectId(record.getActivityId()));
        }

        // 查找activityId对应的activity
        List<Activity> activityList = (List<Activity>) activityRepository.findAllById(activityIdList);
        List<HashMap> resultList = new ArrayList<HashMap>();

        // 把activityDetail和record放到HashMap中，再把HashMap如放入数组
        for (int i = 0; i < records.size(); i++) {
            HashMap<String, Object> takenActivitiesMap = new HashMap<String, Object>();
            takenActivitiesMap.put("activityDetail", activityList.get(i));
            takenActivitiesMap.put("recordDetail", records.get(i));
            resultList.add(takenActivitiesMap);
        }
        return resultList;
    }

    @Override
    public void registerActivity(ObjectId userId, ObjectId activityId, String info) {
        Volunteer volunteer = volunteerRepository.findById(userId).get();

        // 判断是否注册过活动
        List<Record> records = volunteer.getRecords();
        Boolean registered = false;
        for (Record record : records) {
            if (record.getActivityId().equals(activityId.toString()) && record.getState() != "已取消") {
                registered = true;
            }
        }
        if(registered){
            // 已注册过活动
            throw new ServiceException("该活动已被注册");
        }else{
            // 未注册过活动
            Activity activity=activityRepository.findById(activityId).get();
            List<Applicant> applicants= activity.getApplicants();

            // 给该活动添加人员记录
            Applicant applicant=new Applicant();
            applicant.setInfo(info);
            applicant.setState("待审核");
            applicant.setVolunteerId(userId.toString());
            applicants.add(applicant);
            activity.setApplicants(applicants);
            activity.setApplicantNum(activity.getApplicantNum()+1);

            // 给志愿者添加活动记录
            Record record = new Record();
            record.setActivityId(activityId.toString());
            record.setState("待审核");
            records.add(record);
            volunteer.setRecords(records);

            activityRepository.save(activity);
            volunteerRepository.save(volunteer);
        }

    }
}

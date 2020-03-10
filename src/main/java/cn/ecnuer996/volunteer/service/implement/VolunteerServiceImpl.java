package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
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
        List<String> favorList = volunteer.getFavoriteActivity();
        if (favorList.contains(activityId.toString())) {
            favorList.remove(activityId.toString());
        } else {
            favorList.add(activityId.toString());
        }
        volunteer.setFavoriteActivity(favorList);
        volunteerRepository.save(volunteer);
    }

    @Override
    public List<HashMap> listTakenActivities(ObjectId userId) {
        Volunteer volunteer = volunteerRepository.findById(userId).get();
        List<Record> records = volunteer.getRecords();
        List<ObjectId> activityIdList = new ArrayList<ObjectId>();
        for (Record record : records) {
            activityIdList.add(new ObjectId(record.getActivityId()));
        }
        List<Activity> activityList=(List<Activity>) activityRepository.findAllById(activityIdList);
        List<HashMap> resultList=new ArrayList<HashMap>();
        System.out.println(records.toString());
        for (int i = 0; i < records.size(); i++) {
            HashMap<String,Object> takenActivitiesMap=new HashMap<String,Object>();
            takenActivitiesMap.put("activityDetail", activityList.get(i));
            takenActivitiesMap.put("recordDetail",records.get(i));
            resultList.add(takenActivitiesMap);
        }
        return resultList;
    }
}

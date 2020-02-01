package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author 11135
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {
    private VolunteerRepository volunteerRepository;

    @Autowired
    public void setVolunteerRepository(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public String logIn(@NotNull String openid, @NotNull String sessionKey, @NotNull String nickname) {
        Volunteer volunteer=volunteerRepository.findByOpenid(openid);
        if(volunteer!=null) {
            // 已注册用户
            volunteer.setNickname(nickname);
            volunteer.setSessionKey(sessionKey);
            volunteerRepository.save(volunteer);
        }else {
            volunteer=new Volunteer();
            volunteer.setOpenid(openid);
            volunteer.setSessionKey(sessionKey);
            volunteer.setNickname(nickname);
            volunteerRepository.insert(volunteer);
        }
        return volunteerRepository.findByOpenid(openid).getOpenid();
    }
}

package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 11135
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;

    @Override
    public long getCountOfVolunteer() {
        return volunteerRepository.count();
    }

    @Override
    public Volunteer findByNickname(String nickname) {
        Volunteer volunteer=new Volunteer();
        volunteer.setNickname(nickname);
        Example<Volunteer> example=Example.of(volunteer);
        return volunteerRepository.findOne(example).get();
    }

    @Override
    public List<Volunteer> findAll() {
        return volunteerRepository.findAll();
    }
}

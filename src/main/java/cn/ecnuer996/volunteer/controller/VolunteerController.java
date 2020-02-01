package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.VolunteerDao;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Volunteer;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author 11135
 */
@RestController
public class VolunteerController {

    @Autowired
    VolunteerServiceImpl volunteerService;
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerDao volunteerDao;

    @ApiOperation("获取志愿者总数")
    @GetMapping("/volunteer-num")
    public long getVolunteerCount(){
        return volunteerService.getCountOfVolunteer();
    }

    @ApiOperation("用昵称获取志愿者信息")
    @GetMapping("/get-volunteer-by-nickname")
    public Volunteer getVolunteerByNickname(String nickname){
        return volunteerService.findByNickname(nickname);
    }

    @ApiOperation("获取所有志愿者")
    @GetMapping("/all-volunteers")
    public List<Volunteer> getAllVolunteers(){
        return volunteerService.findAll();
    }

    @ApiOperation("测试")
    @GetMapping("find-volunteer-by-example")
    public Volunteer findByExample(String name){
        Volunteer volunteer=new Volunteer();
        volunteer.setNickname(name);
        ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("nickname", ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Volunteer> example=Example.of(volunteer,matcher);
        return volunteerRepository.findOne(example).orElse(volunteer);
    }
}

package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import cn.ecnuer996.volunteer.util.AppUtil;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 11135
 */
@Api(tags = "志愿者相关接口")
@RestController
public class VolunteerController {
    VolunteerServiceImpl volunteerService;
    private VolunteerRepository volunteerRepository;

    @Autowired
    public void setVolunteerService(VolunteerServiceImpl volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Autowired
    public void setVolunteerRepository(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    @ApiOperation("志愿者微信登陆")
    @RequestMapping(value = "/wx-login", method = RequestMethod.POST)
    public @ResponseBody
    Result weixinLogIn(@RequestParam("code") String code,
                           @RequestParam("nickName") String nickName) {

        String volunteerId = volunteerService.logIn(code, nickName);

        Map<String, Object> data = new HashMap<>(2);
        data.put("id", volunteerId);
        data.put("role", "volunteer");
        return ResultGenerator.genSuccessResult(data);
    }

    @ApiOperation("获取志愿者信息")
    @RequestMapping(value = "user-info", method = RequestMethod.GET)
    public Result getUserInfo(@RequestParam("userId") String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.getVolunteerInfo(new ObjectId(userId)));
    }

    @ApiOperation("改变用户收藏活动状态")
    @RequestMapping(value = "change-favor", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID"),
            @ApiImplicitParam(name="activityId",value = "活动ID")
    })
    public Result getOrganizationActivities(String userId,String activityId) {
        volunteerService.changeFavorStatus(new ObjectId(userId),new ObjectId(activityId));
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取用户参加过的活动列表")
    @RequestMapping(value = "get-taken-activities", method = RequestMethod.GET)
    @ApiImplicitParam(name = "userId",value = "用户ID")
    public Result getTakenActivities(String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.listTakenActivities(new ObjectId(userId)));
    }

    @ApiOperation("注册活动")
    @RequestMapping(value = "register-activity", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID"),
            @ApiImplicitParam(name="activityId",value = "活动ID"),
            @ApiImplicitParam(name="info",value = "注册信息")
    })
    public Result getOrganizationActivities(String userId,String activityId, String info) {
        volunteerService.registerActivity(new ObjectId(userId),new ObjectId(activityId),info);
        return ResultGenerator.genSuccessResult();
    }
}

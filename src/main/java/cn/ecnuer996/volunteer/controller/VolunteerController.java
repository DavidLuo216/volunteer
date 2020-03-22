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
    @Autowired
    private VolunteerServiceImpl volunteerService;

    @ApiOperation("志愿者微信登陆")
    @RequestMapping(value = "/wx-login", method = RequestMethod.POST)
    public @ResponseBody
    Result weixinLogIn(@RequestParam("code") String code,
                       @RequestParam("nickName") String nickName) {
        return ResultGenerator.genSuccessResult(volunteerService.logIn(code, nickName));
    }

    @ApiOperation("获取志愿者信息")
    @RequestMapping(value = "user-info", method = RequestMethod.GET)
    public Result getUserInfo(@RequestParam("userId") String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.getVolunteerInfo(new ObjectId(userId)));
    }

    @ApiOperation("改变用户收藏活动状态")
    @RequestMapping(value = "change-favor", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "activityId", value = "活动ID")
    })
    public Result getOrganizationActivities(String userId, String activityId) {
        volunteerService.changeFavorStatus(new ObjectId(userId), new ObjectId(activityId));
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取用户参加过的活动列表")
    @RequestMapping(value = "get-taken-activities", method = RequestMethod.GET)
    @ApiImplicitParam(name = "userId", value = "用户ID")
    public Result getTakenActivities(String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.listTakenActivities(new ObjectId(userId)));
    }

    @ApiOperation("注册活动")
    @RequestMapping(value = "register-activity", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "activityId", value = "活动ID"),
            @ApiImplicitParam(name = "info", value = "注册信息")
    })
    public Result getOrganizationActivities(String userId, String activityId, String info) {
        volunteerService.registerActivity(new ObjectId(userId), new ObjectId(activityId), info);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取用户参加过的活动详情")
    @RequestMapping(value = "get-taken-activity-detail", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动ID"),
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    public Result getTakenActivityDetail(String activityId, String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.getTakenActivityDetail(activityId, userId));
    }

    @ApiOperation("修改志愿者实名信息")
    @RequestMapping(value = "/update-volunteer-info", method = RequestMethod.POST)
    public @ResponseBody
    Result updateVolunteerInfo(@RequestParam("userId") String userId, @RequestParam("nickName") String nickName,
                               @RequestParam("name") String name, @RequestParam("school") String school,
                               @RequestParam("schoolId") String schoolId, @RequestParam("phone") String phone) {
        volunteerService.updateVolunteerInfo(new ObjectId(userId), nickName, name, school, schoolId, phone);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("获取用户收藏过的活动列表")
    @RequestMapping(value = "list-favorite-activities", method = RequestMethod.GET)
    @ApiImplicitParam(name = "userId", value = "用户ID")
    public Result listFavoriteActivities(String userId) {
        return ResultGenerator.genSuccessResult(volunteerService.listFavoriteActivities(new ObjectId(userId)));
    }

    @ApiOperation("发表评论")
    @RequestMapping(value = "/save-comment", method = RequestMethod.POST)
    public @ResponseBody
    Result updateVolunteerInfo(@RequestParam("userId") String userId, @RequestParam("activityId") String activityId,
                               @RequestParam("content") String content) {
        volunteerService.saveComment(new ObjectId(userId), new ObjectId(activityId), content);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("取消报名")
    @RequestMapping(value = "/cancel-registration", method = RequestMethod.GET)
    public @ResponseBody
    Result cancelRegistration(@RequestParam("userId") String userId, @RequestParam("activityId") String activityId) {
        volunteerService.removeActivityRegistration(new ObjectId(userId), new ObjectId(activityId));
        return ResultGenerator.genSuccessResult();
    }
}

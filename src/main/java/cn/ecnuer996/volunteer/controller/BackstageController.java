package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.service.BackstageService;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author xusheng
 */
@Api(tags = "后台管理相关接口")
@RestController
public class BackstageController {
    @Resource
    private BackstageService backstageService;

    @ApiOperation("获取活动申请人列表")
    @RequestMapping(value = "get-applicant-list", method = RequestMethod.GET)
    @ApiImplicitParam(name = "activityId", value = "活动ID", required = true)
    public Result getApplicantList(String activityId) {
        return ResultGenerator.genSuccessResult(backstageService.listApplicants(new ObjectId(activityId)));
    }

    @ApiOperation("处理志愿者报名申请")
    @RequestMapping(value = "change-registration-status", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(name = "activityId", value = "活动ID", required = true),
            @ApiImplicitParam(name = "command", value = "指令 合法值为通过和拒绝", required = true)
    })
    public Result changeRegistrationStatus(String userId, String activityId, String command) {
        backstageService.changeRegistrationStatus(new ObjectId(userId), new ObjectId(activityId), command);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation("组织后台登陆")
    @RequestMapping(value = "admin-login", method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "organizationName", value = "组织名称", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    public Result changeRegistrationStatus(String organizationName, String password) {
        HashMap <String,Object> resultMap=new HashMap<String, Object>(1);
        resultMap.put("organizationId",backstageService.adminLogin(organizationName,password));
        return ResultGenerator.genSuccessResult(resultMap);
    }
}

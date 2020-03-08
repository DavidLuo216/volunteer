package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.service.ActivityService;
import cn.ecnuer996.volunteer.service.implement.ActivityServiceImpl;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 11135
 */
@Api(tags = "活动相关接口")
@RestController
public class ActivityController {
    @Resource(name = "actService")
    ActivityService activityService;

    @ApiOperation("获取推荐活动列表")
    @RequestMapping(value = "recommend-activity", method = RequestMethod.GET)
    public Result getRecommendActivity() {
        List<String> tags = new ArrayList<>();
        tags.add("推荐活动");

        return ResultGenerator.genSuccessResult(activityService.listRecommendActivities(tags));
    }

    @ApiOperation("获取活动详情")
    @RequestMapping(value = "activity-detail", method = RequestMethod.GET)
    public Result getRecommendActivity(String id) {
        return ResultGenerator.genSuccessResult(activityService.getActivityDetail(new ObjectId(id)));
    }

    @ApiOperation("获取某组织活动列表")
    @RequestMapping(value = "organization-activity-list", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id",value = "组织id")
    public Result getOrganizationActivities(String id) {
        return ResultGenerator.genSuccessResult(activityService.listActivitiesByOrganizationId(new ObjectId(id)));
    }
}

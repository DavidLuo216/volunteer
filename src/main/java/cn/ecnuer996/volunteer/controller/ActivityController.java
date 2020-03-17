package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.ActivityRepository;
import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.entity.Activity;
import cn.ecnuer996.volunteer.service.ActivityService;
import cn.ecnuer996.volunteer.service.implement.ActivityServiceImpl;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import io.swagger.annotations.*;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID",required = false),
            @ApiImplicitParam(name="activityId",value = "活动ID", required = true)
    })
    public Result getRecommendActivity(String userId, String activityId) {
        return ResultGenerator.genSuccessResult(activityService.getActivityDetail(activityId, userId));
    }

    @ApiOperation("获取某组织活动列表")
    @RequestMapping(value = "organization-activity-list", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id",value = "组织id")
    public Result getOrganizationActivities(String id) {
        return ResultGenerator.genSuccessResult(activityService.listActivitiesByOrganizationId(new ObjectId(id)));
    }

    @ApiOperation("根据活动名称模糊查询活动")
    @RequestMapping(value = "search-activities", method = RequestMethod.GET)
    @ApiImplicitParam(name = "title",value = "活动名称")
    public Result getActivityListByTitle(String title) {
        return ResultGenerator.genSuccessResult(activityService.findByTitleLike(title));
    }

    @ApiOperation("获取所有活动")
    @RequestMapping(value = "list-all-activities", method = RequestMethod.GET)
    public Result listAllActvities() {
        return ResultGenerator.genSuccessResult(activityService.listActivities());
    }
}

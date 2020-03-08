package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.service.OrganizationService;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 11135
 */
@Api(tags = "志愿组织相关接口")
@RestController
public class OrganizationController {

    @Resource
    private OrganizationService organizationService;

    @ApiOperation("获取组织详情")
    @RequestMapping(value = "organization-detail", method = RequestMethod.GET)
    public Result getRecommendActivity(String id) {
        return ResultGenerator.genSuccessResult(organizationService.getOrganizationDetail(new ObjectId(id)));
    }
}

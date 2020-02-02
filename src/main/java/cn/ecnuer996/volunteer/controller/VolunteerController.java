package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import cn.ecnuer996.volunteer.util.AppUtil;
import cn.ecnuer996.volunteer.util.Result;
import cn.ecnuer996.volunteer.util.ResultGenerator;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import io.swagger.annotations.Api;
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
        String volunteerId;
        try {
            HttpRequest request = HttpRequest.get(AppUtil.wxLoginUrl, true,
                    "appid", AppUtil.appId, "secret", AppUtil.secret, "js_code", code, "grant_type", "authorization_code");
            if (request.code() != 0) {
                throw new Exception("微信登陆api请求失败");
            }
            String body = request.body();
            JSONObject obj = JSONObject.parseObject(body);
            String openid = obj.getString("openid");
            String sessionKey = obj.getString("session_key");
            volunteerId = volunteerService.logIn(openid, sessionKey, nickName);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultGenerator.genFailResult("微信登录出错");
        }
        Map<String, Object> data = new HashMap<>(2);
        data.put("id", volunteerId);
        data.put("role", "volunteer");
        return ResultGenerator.genSuccessResult(data);
    }

    @ApiOperation("获取志愿者信息")
    @RequestMapping(value = "user-info", method = RequestMethod.GET)
    public Result getUserInfo(@RequestParam("userId") String userId) {
        return ResultGenerator.genSuccessResult(volunteerRepository.findById(new ObjectId(userId)));
    }

}

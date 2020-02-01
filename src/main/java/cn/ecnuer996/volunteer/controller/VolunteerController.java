package cn.ecnuer996.volunteer.controller;

import cn.ecnuer996.volunteer.dao.VolunteerRepository;
import cn.ecnuer996.volunteer.service.implement.VolunteerServiceImpl;
import cn.ecnuer996.volunteer.util.AppUtil;
import cn.ecnuer996.volunteer.util.HttpRequest;
import cn.ecnuer996.volunteer.util.JsonResult;
import com.alibaba.fastjson.JSONObject;
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
    @RequestMapping(value="/wx-login",method= RequestMethod.POST)
    public @ResponseBody
    JsonResult weixinLogIn(@RequestParam("code") String code,
                           @RequestParam("nickName") String nickName) {
        String url = AppUtil.wxLoginUrl;
        String param = "appid=" + AppUtil.appId +
                "&secret=" + AppUtil.secret + "&js_code=" + code + "&grant_type=authorization_code";
        String volunteerId;
        try {
            String ret = HttpRequest.sendGet(url, param);
            JSONObject obj = JSONObject.parseObject(ret);
            String openid = obj.getString("openid");
            String sessionKey = obj.getString("session_key");
            volunteerId=volunteerService.logIn(openid, sessionKey, nickName);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(JsonResult.FAIL,"微信登录出错！");
        }
        Map<String,Object> data=new HashMap<>();
        data.put("id",volunteerId);
        data.put("role","volunteer");
        return new JsonResult(data,"微信登录成功！");
    }

    @ApiOperation("获取志愿者信息")
    @RequestMapping(value="user-info",method=RequestMethod.GET)
    public JsonResult getUserInfo(@RequestParam("userId") String userId){
        return new JsonResult(volunteerRepository.findById(new ObjectId(userId)),"获取用户信息成功！");
    }

}

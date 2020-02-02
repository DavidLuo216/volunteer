package cn.ecnuer996.volunteer.service;


/**
 * @author 11135
 */
public interface VolunteerService {

    /**
     * 微信登陆
     * @param openid OpenId
     * @param sessionKey sessionKey
     * @param nickname 用户昵称
     * @return openId
     */
    String logIn(String openid,String sessionKey,String nickname);
}

package cn.ecnuer996.volunteer.service;

/**
 * @author 11135
 */
public interface VolunteerService {

    /**
     * 微信登陆
     * @param openid
     * @param sessionKey
     * @param nickname
     * @return
     */
    String logIn(String openid,String sessionKey,String nickname);
}

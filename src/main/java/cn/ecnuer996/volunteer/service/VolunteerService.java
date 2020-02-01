package cn.ecnuer996.volunteer.service;

import cn.ecnuer996.volunteer.entity.Volunteer;

import java.util.List;

/**
 * @author 11135
 */
public interface VolunteerService {
    /**
     * 返回volunteer集合中文档数目
     * @return
     */
    long getCountOfVolunteer();

    /**
     * 根据昵称查询
     * @param nickname
     * @return
     */
    Volunteer findByNickname(String nickname);

    /**
     * 查询所有志愿者
     * @return
     */
    List<Volunteer> findAll();
}

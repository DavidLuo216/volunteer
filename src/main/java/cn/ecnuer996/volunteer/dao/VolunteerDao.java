package cn.ecnuer996.volunteer.dao;

import cn.ecnuer996.volunteer.entity.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * 使用MongoTemplate而不是MongoRepository
 * @author 11135
 */
@Component
public class VolunteerDao {
    @Autowired
    private MongoTemplate template;

    public Volunteer findByExample(Volunteer volunteer){
        Criteria criteria = Criteria.byExample(volunteer);
        Query query = Query.query(criteria);
        System.out.println("fuck you");
        return template.findOne(query,Volunteer.class,"volunteer");
    }

    public Volunteer findByNickname(String nickname){
        Criteria criteria=Criteria.where("nickname").is(nickname);
        Query query=Query.query(criteria);
        return template.findOne(query,Volunteer.class,"volunteer");
    }
}

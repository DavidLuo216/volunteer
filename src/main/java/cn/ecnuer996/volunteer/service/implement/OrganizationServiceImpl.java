package cn.ecnuer996.volunteer.service.implement;

import cn.ecnuer996.volunteer.dao.OrganizationRepository;
import cn.ecnuer996.volunteer.entity.Organization;
import cn.ecnuer996.volunteer.service.OrganizationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xusheng
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public Organization getOrganizationDetail(ObjectId id) {
        return organizationRepository.findById(id).get();
    }
}

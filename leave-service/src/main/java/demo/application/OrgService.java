package demo.application;

import demo.domain.Repository.OrgRepository;
import demo.domain.entity.Org;
import demo.domain.factory.OrgBuilder;
import demo.domain.factory.OrgBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrgService {

    @Autowired
    OrgRepository orgRepository;
    private final OrgBuilderFactory orgBuilderFactory;

    public OrgService(OrgBuilderFactory orgBuilderFactory) {
        this.orgBuilderFactory = orgBuilderFactory;
    }

    public OrgDTO addOrg(OrgCreateDTO request) {
        //使用工厂模式解决，入参DTO与实体之间的转换关系
        OrgBuilder builder = orgBuilderFactory.builder();
        Org org = builder.tenantId(request.getTenantId())
                .orgTypeCode(request.getOrgTypeCode())
                .leaderId(request.getLeaderId())
                .superiorId(request.getSuperiorId())
                .name(request.getName())
                .build();
        org =orgRepository.save(org);
        return buildOrgDTO(org);
    }

    private OrgDTO buildOrgDTO(Org org) {
        return null;
    }
}



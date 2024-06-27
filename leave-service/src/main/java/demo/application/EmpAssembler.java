package demo.application;

import demo.domain.domainservice.EmpHandler;
import demo.domain.domainservice.OrgValidator;
import demo.domain.entity.Emp;
import demo.domain.entity.Gender;
import demo.domain.entity.SkillLevel;
import demo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpAssembler {

    EmpHandler handler;//领域服务

    OrgValidator orgValidator;//领域服务
    @Autowired
    public EmpAssembler(EmpHandler handler, OrgValidator orgValidator) {
        this.handler = handler;
        this.orgValidator = orgValidator;
    }

    /**
     * 创建员工，入参DTO转换聚合
     * 方式二：采用Assembler装配器模式
     * @param request
     * @param user
     * @return
     */
    Emp fromCreateRequest(CreateEmpRequest request, User user) {
        //校验参数
        validateCreateRequest(request);
        // 生成员工号
        String empNum = handler.generateNum();
        Emp result = new Emp(request.getTenantId(), user.getId());
        result.setNum(empNum);
        result.setIdNum(request.getIdNum());
        result.setDob(request.getDob());
        result.setOrgId(request.getOrgId());
        result.setGender(Gender.ofCode(request.getGenderCode()));
        request.getSkills().forEach(s -> result.addSkill(s.getSkillTypeId(), SkillLevel.ofCode(s.getLevelCode()), s.getDuration(), user.getId()));
        request.getExperiences().forEach(e -> result.addExperience(e.getStartDate(), e.getEndDate(), e.getCompany(), user.getId()));
        return result;
    }

    void validateCreateRequest(CreateEmpRequest request) {
        orgValidator.orgShouldValid(request.getTenantId(), request.getOrgId());
    }

    /**
     * 将领域对象转出DTO
     */
    EmpResponse toResponse(Emp emp) {
        return null;
    }
}

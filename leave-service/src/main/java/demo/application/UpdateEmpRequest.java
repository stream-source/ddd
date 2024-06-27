package demo.application;

import ddd.leave.domain.person.repository.persistence.PersonRepositoryImpl;
import demo.domain.entity.Skill;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdateEmpRequest {

    private String num;

    private String idNum;

    private Long tenantId;

    private Long orgId;

    private LocalDate dob;

    private String genderCode;

    private String levelCode;

    private List<SkillDTO> skills;

    public boolean isSkillAbsent(Skill presentSkill) {
        return !skills.stream().anyMatch(skill -> skill.getSkillTypeId().equals(presentSkill.getSkillTypeId()));
    }
}

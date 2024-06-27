package demo.application;

import demo.domain.entity.Skill;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateEmpRequest {

    private String idNum;

    private Long tenantId;

    private Long orgId;

    private LocalDate dob;

    private String genderCode;

    private String levelCode;

    private List<SkillDTO> skills ;

    private List<ExperienceDTO> experiences;


}

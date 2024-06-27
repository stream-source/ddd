package demo.application;

import demo.domain.entity.SkillLevel;
import lombok.Data;

@Data
public class SkillDTO {
    private Long skillTypeId;
    private String levelCode;
    private int duration;
}

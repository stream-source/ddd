package demo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 实体对象
 */
@Data
public class Skill extends AuditTableEntity{

    private Long id;

    private Long tenantId;

    private Long skillTypeId;

    SkillLevel level;

    private int duration;

    /**
     * 包级别私有权限，将创建对象的权限控制在同一个聚会内
     * @param tenantId
     * @param skillTypeId
     * @param createdAt
     * @param createdBy
     */
    Skill(Long tenantId, Long skillTypeId, LocalDateTime createdAt, Long createdBy) {
        super(createdBy, createdAt);
        this.tenantId = tenantId;
        this.skillTypeId = skillTypeId;
    }

    /**
     * 设置成包级别权限：保证了只有在聚合内部的聚合根、领域服务、工厂等才能对他进行修改，外界只能读取
     * @param skillLevel
     */
    void setLevel(SkillLevel skillLevel) {
        this.level = skillLevel;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }
}

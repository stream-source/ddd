package demo.domain.entity;

import demo.domain.valueobject.Period;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 实体对象
 */
@Data
public class WorkExperience extends AuditTableEntity {
    private Long id;
    private Long tenantId;
    private String company;

    private Period period;

    WorkExperience(Long tenantId, Period period, Long createdBy, LocalDateTime createdAt) {
        super(createdBy, createdAt);
        this.tenantId = tenantId;
        this.company = company;
        this.period = period;
    }

    void setCompany(String company) {
        this.company = company;
    }
}

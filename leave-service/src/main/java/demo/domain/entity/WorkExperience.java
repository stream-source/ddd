package demo.domain.entity;

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
    private LocalDate startDate;
    private LocalDate endDate;

    WorkExperience(Long tenantId,LocalDate startDate, LocalDate endDate, Long createdBy, LocalDateTime createdAt) {
        super(createdBy, createdAt);
        this.tenantId = tenantId;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    void setCompany(String company) {
        this.company = company;
    }
}

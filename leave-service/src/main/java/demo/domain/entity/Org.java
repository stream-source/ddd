package demo.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Org extends AuditTableEntity {

    private Long id;
    private Long tenantId;

    private Long superiorId;

    private String orgTypeCode;

    private String name;

    private OrgStatus status;

    private Long leaderId;



    public Org() {
        status = OrgStatus.EFFECTIVE;
    }

    public boolean isEffective() {
        return status.equals(OrgStatus.EFFECTIVE);
    }
}

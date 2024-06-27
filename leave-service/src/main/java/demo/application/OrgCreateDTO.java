package demo.application;

import demo.domain.entity.OrgStatus;
import lombok.Data;

@Data
public class OrgCreateDTO {

    private Long tenantId;

    private Long superiorId;

    private Long leaderId;

    private String orgTypeCode;

    private String name;

    private OrgStatus status;

    private String orgType;
}

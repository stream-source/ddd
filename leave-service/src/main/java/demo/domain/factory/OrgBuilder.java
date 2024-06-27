package demo.domain.factory;

import demo.domain.domainservice.CommonValidator;
import demo.domain.domainservice.OrgLeaderValidator;
import demo.domain.domainservice.OrgNameValidator;
import demo.domain.domainservice.OrgTypeValidator;
import demo.domain.domainservice.SuperiorValidator;
import demo.domain.entity.Org;

public class OrgBuilder {
    //用到的 validator
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    //用这些属性保存创建对象用到的参数
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private Long createdBy;

    public OrgBuilder(
            CommonValidator commonValidator,
            OrgTypeValidator orgTypeValidator,
            SuperiorValidator superiorValidator,
            OrgNameValidator orgNameValidator,
            OrgLeaderValidator orgLeaderValidator
    ) {
        this.commonValidator = commonValidator;
        this.orgTypeValidator = orgTypeValidator;
        this.superiorValidator = superiorValidator;
        this.orgNameValidator = orgNameValidator;
        this.orgLeaderValidator = orgLeaderValidator;
    }

    // 为builder 的 tenant 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder tenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }


    // 为builder 的 superiorId 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder superiorId(Long superiorId) {
        this.superiorId = superiorId;
        return this;
    }


    // 为builder 的 orgTypeCode 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder orgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
        return this;
    }

    // 为builder 的 leaderId 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder leaderId(Long leaderId) {
        this.leaderId = leaderId;
        return this;
    }

    // 为builder 的 name 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder name(String name) {
        this.name = name;
        return this;
    }

    // 为builder 的 createdBy 属性赋值，然后返回自己，以便实现链式调用
    public OrgBuilder createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }


    public Org build() {
        validate();

        Org org = new Org();
        org.setTenantId(tenantId);
        org.setSuperiorId(superiorId);
        org.setOrgTypeCode(orgTypeCode);
        org.setLeaderId(leaderId);
        org.setName(name);
        org.setCreatedBy(createdBy);
        return org;
    }

    private void validate() {
        commonValidator.tenantShouldValid(tenantId);
        orgTypeValidator.verify(tenantId, orgTypeCode);
        superiorValidator.verify(tenantId, superiorId, orgTypeCode);
        orgLeaderValidator.verify(tenantId, leaderId);
        orgNameValidator.verify(tenantId, name, superiorId);
    }


}

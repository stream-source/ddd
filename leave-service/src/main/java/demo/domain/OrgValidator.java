//package demo.domain;
//
//import demo.application.OrgDTO;
//
//public class OrgValidator {
//
//    private final CommonValidator commonValidator;
//    private final OrgTypeValidator orgTypeValidator;
//    private final SuperiorValidator superiorValidator;
//    private final OrgNameValidator orgNameValidator;
//    private final OrgLeaderValidator orgLeaderValidator;
//
//    public OrgValidator(
//            CommonValidator commonValidator,
//            OrgTypeValidator orgTypeValidator,
//            SuperiorValidator superiorValidator,
//            OrgNameValidator orgNameValidator,
//            OrgLeaderValidator orgLeaderValidator
//    ) {
//        this.commonValidator = commonValidator;
//        this.orgTypeValidator = orgTypeValidator;
//        this.superiorValidator = superiorValidator;
//        this.orgNameValidator = orgNameValidator;
//        this.orgLeaderValidator = orgLeaderValidator;
//    }
//
//    /**
//     * 统一的入口验证
//     * @param request
//     */
//    public void validate(OrgDTO request) {
//        final Long tenant = request.getTenantId();
//        commonValidator.tenantShouldValid(tenant);
//        orgLeaderValidator.verify(tenant, request.getLeaderId());
//        orgTypeValidator.verify(tenant, request.getOrgType());
//        superiorValidator.verify(tenant, request.getSuperiorId(), request.getOrgType());
//        orgNameValidator.verify(tenant, request.getName(), request.getSuperiorId());
//    }
//}

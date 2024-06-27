//package demo.domain;
//
//import demo.application.OrgDTO;
//import demo.domain.entity.Org;
//
//public class OrgFactory {
//    private final OrgValidator orgValidator;
//
//    public OrgFactory(OrgValidator orgValidator) {
//        this.orgValidator = orgValidator;
//    }
//
//    public Org build(OrgDTO orgDTO) {
//        orgValidator.validate(orgDTO);
//        return buildOrg(orgDTO);
//    }
//
//
//
//    private Org buildOrg(OrgDTO orgDTO) {
//        return null;
//    }
//}

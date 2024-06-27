package demo.domain.factory;

import demo.domain.domainservice.CommonValidator;
import demo.domain.domainservice.OrgLeaderValidator;
import demo.domain.domainservice.OrgNameValidator;
import demo.domain.domainservice.OrgTypeValidator;
import demo.domain.domainservice.SuperiorValidator;
import org.springframework.stereotype.Component;

@Component
public class OrgBuilderFactory {
    private final CommonValidator commonValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    public OrgBuilderFactory(
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

    /**
     * 方式一：使用建造者模式创建聚合
     * @return
     */
    public OrgBuilder builder() {
        return new OrgBuilder(commonValidator, orgTypeValidator, superiorValidator, orgNameValidator, orgLeaderValidator);
    }
}

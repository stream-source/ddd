package demo.domain.domainservice;

import demo.domain.entity.Org;

public class CancelOrgValidator {

    public void OnlyEffectiveOrgCanBeCancelled(Org org) {
        //不再依赖 Org 的内部状态
        if (!org.isEffective()) {
            throw new RuntimeException("该组织不是有效状态，不能撤销！");
        }
    }
}

package demo.adapter.driven;

import demo.domain.entity.*;
import demo.domain.valueobject.EmpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 为什么要建立子类继承聚合呢？
 * 从数据库重建员工（Emp）聚合的过程中，
 * 当我们调用 Emp 的一些方法赋值的时候，会触发业务规则的校验。比如说，调用 addSkill()
 * 增加技能的时候，会触发“技能类型不允许重复”的校验。
 * 重建聚合的时候，是否应该进行这种校验呢？
 *      取决于数据的“干净程度”。如果数据库中的数据比较“脏”，也就是说数据库里很多数据已经违反了业务规则，那么，可能在重建聚合时再校验一遍业务规则是可取的，这样可以找出脏数据错误。
 *      据库是比较干净的。这时候，如果每次从数据库取数据都要校验一遍，就会无谓地影响性能。
 *      解决方案：
 *          先把 Emp 中的属性都改成 protected 的，然后写一个 Emp 的子类，这个子类中的方法也可以设置 Emp 的值，但不调用业务规则；
 */
public class RebuiltEmp extends Emp {
    RebuiltEmp(Long tenantId, Long id, LocalDateTime create_at, long created_by){
        super(tenantId, id, create_at, created_by);
        //由于是从数据库重建，所以状态默认为"不变"
        this.changingStatus = ChangingStatus.UNCHANGED;
    }

    //包级权限，并且用 resetXxx 命名
    RebuiltEmp resetOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }
    RebuiltEmp resetNum(String num) {
        this.num = num;
        return this;
    }
    RebuiltEmp resetIdNum(String idNum) {
        this.idNum = idNum;
        return this;
    }
    RebuiltEmp resetName(String name) {
        this.name = name;
        return this;
    }
    RebuiltEmp resetGender(Gender gender) {
        this.gender = gender;
        return this;
    }
    RebuiltEmp resetDob(LocalDate dob) {
        this.dob = dob;
        return this;
    }
    RebuiltEmp resetStatus(EmpStatus status) {
        this.empStatus = status;
        return this;
    }

    public RebuiltEmp reAddSkill(Long id, Long skillTypeId, SkillLevel level, int duration, LocalDateTime createdAt, long createdBy) {
//        RebuiltSkill newSkill = new RebuiltSkill(tenantId, id, skillTypeId, createdAt, createdBy);
//        newSkill.resetLevel(level);
//        newSkill.resetDuration(duration);
//        skills.add(newSkill);
        return this;
    }
}

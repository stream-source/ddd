package demo.domain.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 员工：聚合根
 */
@Data
public class Emp extends AggregateRoot {
    protected Long id; //只读
    protected Long tenantId; //只读

    protected String name;

    /**
     * 聚合间，使用ID关联对象
     */
    protected Long orgId; //读写
    /**
     * 聚合内部使用，对象关联
     */

    protected Gender gender;//读写

    protected EmpStatus empStatus;//读写
    protected String num;//读写
    protected String idNum;//读写
    protected String mobile;//读写
    protected String position;//读写
    protected LocalDate dob;//读写

    /**
     * 以下均可以读写
     */
    protected List<Skill> skills;

    protected List<WorkExperience> experiences;

    protected List<String> postCodes;

    public Emp(Long tenantId, Long orgId) {
        this.tenantId = tenantId;
        this.orgId = orgId;
    }

    public Emp(Long tenantId, Long orgId, LocalDateTime create_at, long created_by) {
        this.tenantId = tenantId;
        this.orgId = orgId;
        this.createdAt = create_at;
        this.createdBy = created_by;
    }

    /**
     * 实体的行为方法，不一定要使用setXXX()
     */
    //转正
    void becomeRegular() {
        // 调用业务规则: 试用期的员工才能被转正
        onlyProbationCanBecomeRegular();
        empStatus = EmpStatus.REGULAR;
    }


    public void terminate() {
        // 调用业务规则: 已经终止的员工不能再次终止
        shouldNotTerminateAgain();
        empStatus = EmpStatus.TERMINATE;
    }

    private void onlyProbationCanBecomeRegular() {
        if (empStatus != EmpStatus.PROBATION) {
            throw new RuntimeException("试用期员工才能转正！");
        }
    }

    private void shouldNotTerminateAgain() {
        if (empStatus == EmpStatus.TERMINATE) {
            throw new RuntimeException("员工已经终止了");
        }
    }

    private void skillTypeShouldNotDuplicated(Long newSkillTypeId) {
        if (skills.stream().anyMatch(
                s -> s.getSkillTypeId() == newSkillTypeId)) {
            throw new RuntimeException("同一技能不能录入两次！");
        }
    }

    /**
     * 对非聚合实体的封装
     */
    public Optional<Skill> getSkill(Long skillTypeId) {
        return skills.stream()
                .filter(s -> Objects.equals(s.getSkillTypeId(), skillTypeId))
                .findAny();
    }


    /**
     * 而不是直接将list集合直接返回，因为外部拿到集合可以对元素进行增删改的操作；
     * 故此处利用集合的特性，将其封装成为不可变集合
     * @return
     */
    public List<Skill> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    /**
     * 添加工作经验，，，，对非聚合实体，如何优化入参参数？？？？
     * 控制外界无法直接创建非聚合实体，全部通过聚合根操作
     * 技能和工作经验的两条规则，必须从整个聚合层面才能验证，所以无法在 Skill 和WorkExperience 两个类内部实现，只能在聚合根（Emp）里实现
     * @param skillTypeId
     * @param level
     * @param duration
     * @param userId
     */
    public void addSkill(Long skillTypeId, SkillLevel level, int duration, Long userId) {
        //调用业务规则：同一个技能不能录入两次
        skillTypeShouldNotDuplicated(skillTypeId);
        //注意，这里使用的是包级别授权，若使用私有化构造函数，则无法创建
        Skill newSkill = new Skill(tenantId, skillTypeId, LocalDateTime.now(), userId);
        newSkill.setLevel(level);
        newSkill.setDuration(duration);
        skills.add(newSkill);
    }

    /**
     * 行为方法：添加工作经验
     * @param startDate
     * @param endDate
     * @param company
     * @param userId
     */
    public void addExperience(LocalDate startDate, LocalDate endDate, String company, Long userId){
        // 调用业务规则: 工作经验的时间段不能重叠
        durationShouldNotOverlap(startDate, endDate);
        WorkExperience newExperience = new WorkExperience(tenantId, startDate, endDate, userId, LocalDateTime.now());
        newExperience.setCompany(company);
        experiences.add(newExperience);
    }


    /**
     * 不变规则：校验工作经验；如果聚合根没有足够的数据，需要从数据库中获取，则需要将不变规则移入领域服务中；
     * @param startDate
     * @param endDate
     */
    private void durationShouldNotOverlap(LocalDate startDate, LocalDate endDate) {
        if (experiences.stream().anyMatch(e -> overlap(e, startDate, endDate))) {
            throw new RuntimeException("工作经验的时间段不能重叠!");
        }
    }
    private boolean overlap(WorkExperience experience, LocalDate otherStart, LocalDate otherEnd) {
        LocalDate thisStart = experience.getStartDate();
        LocalDate thisEnd = experience.getEndDate();
        return otherStart.isBefore(thisEnd) && otherEnd.isAfter(thisStart);
    }


    /**
     * 修改技能
     */
    public Emp updateSkill(Long skillTypeId, SkillLevel level, int duration, Long userId) {
        Skill theSkill = this.getSkill(skillTypeId).orElseThrow(() -> new RuntimeException("不存在要修改的skillTypeId!"));
        if (theSkill.getLevel() != level || theSkill.getDuration() != duration) {
            theSkill.setLevel(level);
            theSkill.setDuration(duration);
            theSkill.setLastUpdatedBy(userId);
            theSkill.setLastUpdatedAt(LocalDateTime.now());
            theSkill.toUpdate(); //设置修改状态
        }
        return this;
    }

    /**
     * 删除技能
     * @param skillTypeId
     * @return
     */

    public Emp deleteSkill(Long skillTypeId) {
        this.getSkill(skillTypeId)
                .orElseThrow(() -> new RuntimeException("不存在要删除的skillTypeId!"))
                .toDelete(); //设置修改状态
        return this;
    }



}

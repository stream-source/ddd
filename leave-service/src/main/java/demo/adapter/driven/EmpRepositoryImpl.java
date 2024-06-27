package demo.adapter.driven;

import demo.domain.Repository.EmpRepository;
import demo.domain.entity.Emp;
import demo.domain.entity.Gender;
import demo.domain.entity.Skill;
import demo.domain.entity.WorkExperience;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

/**
 * 仓库是针对聚合整体的，而不是针对单独的表的。也就是说，聚合和它的仓库有一一对应关系
 */
@Repository
public class EmpRepositoryImpl implements EmpRepository {


    /**
     * 注入其他表对象的接口实现，即技术组件的接口
     * @param emp
     */
    @Override
    public void save(Emp emp) {
        saveEmp(emp); // 插入\修改 同时支撑 emp 表
        emp.getSkills().forEach(s -> insertSkill(s, emp.getId())); // 插入 skill 表
        emp.getExperiences().forEach(e -> insertWorkExperience(e, emp.getId())); // 插入 work_experience 表
        emp.getPostCodes().forEach(p -> insertEmpPost(p, emp.getId()));
    }

    /**
     * 支撑修改、新增
     * @param emp
     */
    private  void saveEmp(Emp emp) {
        switch (emp.getChangingStatus()) {
            case NEW:
                insertEmpRecord(emp);
                break;
            case UPDATED:
                updateEmpRecord(emp);
                break;
        }
    }

    private void insertEmpRecord(Emp emp) {
//        Map<String, Object> parms = Map.of(
//                "tenant_id", emp.getTenantId()
//                , "org_id", emp.getOrgId()
//                , "num", emp.getNum()
//                , "id_num", emp.getIdNum()
//                , "name", emp.getName()
//                , "gender", emp.getGender().code()
//                , "dob", emp.getDob()
//                , "status", emp.getStatus().code()
//                , "created_at", emp.getCreatedAt()
//                , "created_by", emp.getCreatedBy()
//        );
//        Number createdId = empInsert.executeAndReturnKey(parms);
//        forceSet(emp, "id", createdId.longValue());
    }

    private void updateEmpRecord(Emp emp) {}

    @Override
    public Optional<Emp> findById(Long tenantId, Long empId) {
        Optional<RebuiltEmp> empMayBe = retrieveEmp(tenantId, empId);
        if (empMayBe.isPresent()) {
            RebuiltEmp emp = empMayBe.get();
            retrieveSkills(emp);
            retrieveExperiences(emp);
            retrievePosts(emp);
            return Optional.of(emp);
        } else {
            return Optional.empty();
        }
    }

    private void retrievePosts(RebuiltEmp emp) {

    }

    private void retrieveExperiences(RebuiltEmp emp) {

    }

    private void retrieveSkills(RebuiltEmp emp) {

    }

    private Optional<RebuiltEmp>  retrieveEmp(Long tenantId, Long id) {
        String sql = " select org_id, num, id_num, name "
                + " , gender_code, dob, status_code "
                + " from emp "
                + " where id = ? and tenant_id = ? ";
        RebuiltEmp emp = null;
//                (rs, rowNum) -> {
//                    RebuiltEmp newEmp = new RebuiltEmp(tenantId
//                            , id
//                            , rs.getTimestamp("create_at").toLocalDateTime()
//                            , rs.getLong("created_by"));
//                    newEmp.resetOrgId(rs.getLong("org_id"))
//                            .resetNum(rs.getString("num"))
//                            .resetIdNum(rs.getString("id_num"))
//                            .resetName(rs.getString("name"))
//                            .resetGender(Gender.ofCode(
//                                    rs.getString("gender_code")))
//                            .resetDob(rs.getDate("dob").toLocalDate())
//                            .resetStatus(EmpStatus.ofCode(
//                                    rs.getString("status_code")));
//                    return newEmp;
//                },
//                id, tenantId);
        return Optional.ofNullable(emp);
    }

    private void insertEmp(Emp emp) {
//        Map<String, Object> parms = Map.of(
//                "tenant_id", emp.getTenantId()
//                , "org_id", emp.getOrgId()
//                , "num", emp.getNum()
//                , "id_num", emp.getIdNum()
//                , "name", emp.getName()
//                , "gender", emp.getGender().code()
//                , "dob", emp.getDob()
//                , "status", emp.getStatus().code()
//                , "created_at", emp.getCreatedAt()
//                , "created_by", emp.getCreatedBy()
//        );
//        Number createdId = empInsert.executeAndReturnKey(parms);
////通过反射为私有 id 属性赋值
//        forceSet(emp, "id", createdId.longValue());

    }

    private void insertWorkExperience(WorkExperience experience, Long empId) {
// 类似 insertEmp...
    }

    private void insertSkill(Skill skill, Long empId) {
// 类似 insertEmp...
    }

    private void insertEmpPost(String empPost, Long empId) {
// 类似 insertEmp...
    }
}

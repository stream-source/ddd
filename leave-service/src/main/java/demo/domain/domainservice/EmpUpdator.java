package demo.domain.domainservice;

import demo.application.SkillDTO;
import demo.application.UpdateEmpRequest;
import demo.domain.entity.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class EmpUpdator {

    /**
     * 更新员工信息
     * @param emp
     * @param request
     * @param user
     */
    public void update(Emp emp, UpdateEmpRequest request, User user) {
        emp.setNum(request.getNum());
        emp.setIdNum(request.getIdNum());
        emp.setDob(request.getDob());
        emp.setGender(Gender.ofCode(request.getGenderCode()));
        emp.setLastUpdatedAt(LocalDateTime.now());
        emp.setLastUpdatedBy(user.getId());
        emp.toUpdate(); // 设置修改状态
        updateSkills(emp, request, user.getId());
        updateExperiences(emp, request, user.getId());
    }

    private void updateExperiences(Emp emp, UpdateEmpRequest request, Long id) {

    }

    //对技能的增删改
    private void updateSkills(Emp emp, UpdateEmpRequest request, Long userId) {
        //删除不存在的技能。逻辑是，比较请求参数（request）和当前员工聚合里的技能
        deleteAbsentSkills(emp, request);
        //处理请求参数里存在的技能。如果请求参数里的技能在当前聚合里存在，就更改，否则就增加。由于既可能是更改，也可能是增加，所以方法名用了 operate (操作）
        operatePresentSkills(emp, request, userId);
    }

    /**
     * 删除目前聚合里有，但是请求参数里没有的技能
     */
    private void deleteAbsentSkills(Emp emp, UpdateEmpRequest request) {
        emp.getSkills().forEach(presentSkill -> {
            if (request.isSkillAbsent(presentSkill)) {
                emp.deleteSkill(presentSkill.getSkillTypeId());
            }
        });
    }

    //增加或修改技能
   public void operatePresentSkills(Emp emp, UpdateEmpRequest request, Long userId) {
        for (SkillDTO skill : request.getSkills()) {
            Optional<Skill> skillMaybe = emp.getSkill(skill.getSkillTypeId());
            if(skillMaybe.isPresent()) {
                emp.updateSkill(skill.getSkillTypeId(), SkillLevel.ofCode(skill.getLevelCode()), skill.getDuration(), userId);
            } else {
                emp.addSkill(skill.getSkillTypeId(), SkillLevel.ofCode(skill.getLevelCode()), skill.getDuration(), userId);
            }
        }
    }
}


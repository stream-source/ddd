package demo.domain.entity;

/**
 * 实体对象
 */
public enum SkillLevel {

    BASIC,
    INTERMEDIATE,
    ADVANCED
    ;


    public static SkillLevel ofCode(String levelCode) {
        return SkillLevel.valueOf(levelCode);
    }
}

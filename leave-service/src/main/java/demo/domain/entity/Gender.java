package demo.domain.entity;

public enum Gender {
    MALE,
    FEMALE,
    UNDEFINED
    ;

    public static Gender ofCode(String genderCode) {
        return Gender.valueOf(genderCode);
    }
}

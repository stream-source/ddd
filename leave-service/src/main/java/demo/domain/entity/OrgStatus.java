package demo.domain.entity;

public enum OrgStatus {
    EFFECTIVE("有效");

    private String name;

    private OrgStatus(String name) {
        this.name = name;
    }


}

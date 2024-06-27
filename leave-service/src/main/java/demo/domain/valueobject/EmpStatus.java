package demo.domain.valueobject;

public enum EmpStatus {
    REGULAR("REG"), // 正式
    PROBATION("PRO"), // 试用期
    TERMINATED("TER"); // 终止
    private final String code;
    EmpStatus(String code) {
        this.code = code;
    }
    public String code() {
        return code;
    }

    /**
     * 员工状态：作为值对象，具备行为方法
     * @return
     */

    public EmpStatus becomeRegular() {
        if (this != PROBATION) {
            throw new RuntimeException("试用期员工才能转正！");
        }
        return REGULAR;
    }
    public EmpStatus terminate() {
        if (this == TERMINATED) {
            throw new RuntimeException("已经终止的员工不能再次终止！");
        }
        return TERMINATED;
    }
}

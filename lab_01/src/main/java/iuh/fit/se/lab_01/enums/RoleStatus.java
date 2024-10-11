package iuh.fit.se.lab_01.enums;

public enum RoleStatus {
    ACTIVE(1), INACTIVE(0), DELETED(-1);
    private int value;
    private RoleStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

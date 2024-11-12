package vn.edu.iuh.backend.enums;

public enum SkillType {
    SOFT_SKILL((byte) 0), TECHNICAL_SKILL((byte) 1), UNSPECIFIED((byte) 2);
    private int value;

    SkillType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

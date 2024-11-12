package vn.edu.iuh.backend.enums;

public enum SkillLevel {
    BEGINNER((byte) 0), INTERMEDIATE((byte) 1), ADVANCED((byte) 2), PROFESSIONAL((byte) 3), MASTER((byte) 4);
    private int value;

    SkillLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

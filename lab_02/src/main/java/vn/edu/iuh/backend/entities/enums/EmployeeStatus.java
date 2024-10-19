package vn.edu.iuh.backend.entities.enums;

public enum EmployeeStatus {
    INACTIVE (0), ACTIVE (1), TERMINATED (-1);
    int value;

    EmployeeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

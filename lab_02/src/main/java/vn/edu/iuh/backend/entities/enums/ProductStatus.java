package vn.edu.iuh.backend.entities.enums;

public enum ProductStatus {
    ACTIVE(1), INACTIVE(0), DELETED(-1);
    private int value;

    ProductStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

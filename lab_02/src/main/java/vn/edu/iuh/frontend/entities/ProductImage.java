package vn.edu.iuh.frontend.entities;

import java.util.Objects;

public class ProductImage {
    private long image_id;
    private String alternative;
    private String path;
    private Product product;


    public ProductImage() {
    }

    public long getImage_id() {
        return image_id;
    }

    public void setImage_id(long image_id) {
        this.image_id = image_id;
    }

    public String getAlternative() {
        return alternative;
    }

    public void setAlternative(String alternative) {
        this.alternative = alternative;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImage that = (ProductImage) o;
        return image_id == that.image_id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(image_id);
    }
}

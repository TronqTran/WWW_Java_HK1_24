package vn.edu.iuh.backend.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import vn.edu.iuh.backend.entities.enums.ProductStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductConverter implements AttributeConverter<ProductStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        if (productStatus != null) {
            return productStatus.getValue();
        }
        return null;
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer integer) {
        if (integer != null) {
            return Stream.of(ProductStatus.values())
                    .filter(c -> c.getValue() == integer)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
        return null;
    }
}

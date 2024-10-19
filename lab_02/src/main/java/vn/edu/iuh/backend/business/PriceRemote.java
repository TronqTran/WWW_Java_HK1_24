package vn.edu.iuh.backend.business;

import vn.edu.iuh.backend.entities.ProductPrice;

public interface PriceRemote {
    void addPrice(ProductPrice price);
    void updatePrice(ProductPrice price);
}

package com.keelient.catalog_service.mappers;

import com.keelient.catalog_service.models.Product;
import com.keelient.catalog_service.models.ProductEntity;

public class ProductMapper {

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}

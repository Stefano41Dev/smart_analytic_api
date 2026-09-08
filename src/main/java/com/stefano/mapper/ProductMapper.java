package com.stefano.mapper;

import com.stefano.dto.product.ProductDtoRequest;
import com.stefano.dto.product.ProductDtoResponse;
import com.stefano.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductDtoResponse toResponse(Product product) {
        return ProductDtoResponse.builder().id(product.getId()).name(product.getName())
                .brandId(product.getBrand().getId()).categoryId(product.getCategory().getId())
                .price(product.getPrice()).stock(product.getStock()).sku(product.getSku()).build();
    }

    public Product toEntity(ProductDtoRequest request) {
        return Product.builder()
                .name(request.name())
                .stock(request.stock())
                .price(request.price())
                .sku(request.sku())
                .build();
    }
}

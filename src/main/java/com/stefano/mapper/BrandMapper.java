package com.stefano.mapper;

import com.stefano.dto.brand.BrandDtoResponse;
import com.stefano.models.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public BrandDtoResponse toResponse(Brand brand) {
        return BrandDtoResponse.builder()
            .id(brand.getId())
            .name(brand.getName())
            .build();
    }
}

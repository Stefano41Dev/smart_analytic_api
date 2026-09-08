package com.stefano.dto.brand;

import lombok.Builder;

@Builder
public record BrandDtoResponse(
        Long id,
        String name
) {
}

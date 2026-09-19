package com.stefano.models.dto.brand;

import lombok.Builder;

@Builder
public record BrandDtoRequest(
        String name
) {
}

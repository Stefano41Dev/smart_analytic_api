package com.stefano.dto.brand;

import lombok.Builder;

@Builder
public record BrandDtoRequest(
        String name
) {
}

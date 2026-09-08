package com.stefano.dto.category;

import lombok.Builder;

@Builder
public record CategoryDtoResponse(
        Long id,
        String name
) {
}

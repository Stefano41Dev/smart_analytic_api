package com.stefano.models.dto.category;

import lombok.Builder;

@Builder
public record CategoryDtoRequest(
        String name
) {
}

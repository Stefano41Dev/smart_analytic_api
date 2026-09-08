package com.stefano.dto.category;

import lombok.Builder;

@Builder
public record CategoryDtoRequest(
        String name
) {
}

package com.stefano.models.dto.client;

import lombok.Builder;

@Builder
public record ClientDtoResponse(
        Long id,
        String names,
        String lastnames,
        String dni,
        String gmail
) {
}

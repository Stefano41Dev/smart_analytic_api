package com.stefano.dto.client;

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

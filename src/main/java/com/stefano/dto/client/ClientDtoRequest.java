package com.stefano.dto.client;

import lombok.Builder;

@Builder
public record ClientDtoRequest(
        String names,
        String lastnames,
        String dni,
        String gmail
) {
}

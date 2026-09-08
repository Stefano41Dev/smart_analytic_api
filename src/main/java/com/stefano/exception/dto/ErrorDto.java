package com.stefano.exception.dto;

import java.time.LocalDateTime;

public record ErrorDto(
        String message,
        LocalDateTime date
) {
}

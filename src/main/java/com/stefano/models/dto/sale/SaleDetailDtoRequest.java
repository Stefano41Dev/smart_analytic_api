package com.stefano.models.dto.sale;

import lombok.Builder;

@Builder
public record SaleDetailDtoRequest(
        Long productId,
        Integer quantity
) {
}

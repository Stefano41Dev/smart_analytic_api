package com.stefano.dto.sale;

import lombok.Builder;

@Builder
public record SaleDetailDtoRequest(
        Long productId,
        Integer quantity
) {
}

package com.stefano.dto.sale;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record SaleDetailDtoResponse(
        Long id,
        Long productId,
        Integer quantity,
        BigDecimal priceUnit,
        BigDecimal subtotal,
        BigDecimal total
) {
}

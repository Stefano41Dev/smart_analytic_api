package com.stefano.dto.sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record SaleDtoResponse(
        Long id,
        Long clientId,
        BigDecimal subtotal,
        BigDecimal igv,
        BigDecimal total,
        LocalDateTime createdAd,
        List<SaleDetailDtoResponse> saleDetails
) {
}

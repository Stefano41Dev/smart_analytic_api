package com.stefano.models.dto.sale;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record SaleSumary(
        BigDecimal totalRevenue,
        Long totalSales,
        Long totalProductsSold,
        BigDecimal averageSale
) {
}

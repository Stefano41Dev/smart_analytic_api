package com.stefano.dto.product;

import java.math.BigDecimal;
import lombok.Builder;

@Builder
public record ProductDtoRequest(
        String name,
        Long brandId,
        Long categoryId,
        BigDecimal price,
        Integer stock,
        String sku
) {
}

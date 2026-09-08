package com.stefano.dto.sale;

import java.util.List;
import lombok.Builder;

@Builder
public record SaleDtoRequest(
        Long clientId,
        List<SaleDetailDtoRequest> saleDetails
) {
}

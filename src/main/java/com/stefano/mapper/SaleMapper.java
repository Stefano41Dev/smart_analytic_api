package com.stefano.mapper;

import com.stefano.dto.sale.SaleDetailDtoResponse;
import com.stefano.dto.sale.SaleDtoResponse;
import com.stefano.models.Sale;
import com.stefano.models.SaleDetail;
import org.springframework.stereotype.Component;

@Component
public class SaleMapper {
    public SaleDtoResponse toResponse(Sale sale) {
        return SaleDtoResponse.builder()
                .id(sale.getId())
                .clientId(sale.getClient().getId())
                .subtotal(sale.getSubtotal())
                .igv(sale.getIgv()).total(sale.getTotal())
                .createdAd(sale.getCreatedAd())
                .saleDetails(sale.getSaleDetails().stream().map(this::toDetailResponse).toList()).build();
    }

    private SaleDetailDtoResponse toDetailResponse(SaleDetail detail) {
        return SaleDetailDtoResponse.builder()
                .id(detail.getId())
                .productId(detail.getProduct().getId())
                .quantity(detail.getQuantity())
                .priceUnit(detail.getPriceUnit())
                .subtotal(detail.getSubtotal())
                .total(detail.getTotal())
                .build();
    }
}

package com.stefano.service.impl;

import com.stefano.models.dto.sale.SaleSumary;
import com.stefano.repository.SaleRepository;
import com.stefano.service.AnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AnalyticServiceImpl implements AnalyticService {
    private final SaleRepository saleRepository;
    @Override
    public SaleSumary getSummary() {

        BigDecimal sumTotalSale = saleRepository.sumTotal();
        Long totalSales = saleRepository.count();
        Long totalProductSold = saleRepository.totalProductsSold();
        BigDecimal averageSale = saleRepository.averageSale();

        return SaleSumary.builder()
                .totalRevenue(sumTotalSale)
                .totalSales(totalSales)
                .totalProductsSold(totalProductSold)
                .averageSale(averageSale)
                .build();
    }
}

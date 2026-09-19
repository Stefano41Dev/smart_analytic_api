package com.stefano.service;

import com.stefano.models.dto.sale.SaleDtoRequest;
import com.stefano.models.dto.sale.SaleDtoResponse;
import java.util.List;

public interface SaleService {
    SaleDtoResponse create(SaleDtoRequest request);
    List<SaleDtoResponse> findAll();
    SaleDtoResponse findById(Long id);
    SaleDtoResponse update(Long id, SaleDtoRequest request);
}

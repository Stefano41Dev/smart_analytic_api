package com.stefano.service;

import com.stefano.models.dto.brand.BrandDtoRequest;
import com.stefano.models.dto.brand.BrandDtoResponse;
import java.util.List;

public interface BrandService {
    BrandDtoResponse create(BrandDtoRequest request);
    List<BrandDtoResponse> findAll();
    BrandDtoResponse findById(Long id);
    BrandDtoResponse update(Long id, BrandDtoRequest request);
    void delete(Long id);
}

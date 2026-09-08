package com.stefano.service;

import com.stefano.dto.product.ProductDtoRequest;
import com.stefano.dto.product.ProductDtoResponse;
import java.util.List;

public interface ProductService {
    ProductDtoResponse create(ProductDtoRequest request);
    List<ProductDtoResponse> findAll();
    ProductDtoResponse findById(Long id);
    ProductDtoResponse update(Long id, ProductDtoRequest request);
    void delete(Long id);
}

package com.stefano.service;

import com.stefano.dto.category.CategoryDtoRequest;
import com.stefano.dto.category.CategoryDtoResponse;
import java.util.List;

public interface CategoryService {
    CategoryDtoResponse create(CategoryDtoRequest request);
    List<CategoryDtoResponse> findAll();
    CategoryDtoResponse findById(Long id);
    CategoryDtoResponse update(Long id, CategoryDtoRequest request);
    void delete(Long id);
}

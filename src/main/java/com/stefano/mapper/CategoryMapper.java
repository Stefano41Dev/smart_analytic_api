package com.stefano.mapper;

import com.stefano.dto.category.CategoryDtoResponse;
import com.stefano.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDtoResponse toResponse(Category category) {
        return CategoryDtoResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

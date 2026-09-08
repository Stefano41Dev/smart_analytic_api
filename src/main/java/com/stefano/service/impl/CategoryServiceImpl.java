package com.stefano.service.impl;

import com.stefano.dto.category.CategoryDtoRequest;
import com.stefano.dto.category.CategoryDtoResponse;
import com.stefano.models.Category;
import com.stefano.repository.CategoryRepository;
import com.stefano.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    public CategoryDtoResponse create(CategoryDtoRequest request) {
        return toResponse(repository.save(Category.builder()
                .name(request.name())
                .build()));
    }
    public List<CategoryDtoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    public CategoryDtoResponse findById(Long id) {
        return toResponse(get(id));
    }
    public CategoryDtoResponse update(Long id, CategoryDtoRequest request) {
        Category category = get(id); category.setName(request.name()); return toResponse(repository.save(category));
    }
    public void delete(Long id) {
        repository.delete(get(id));
    }
    private Category get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
    }
    private CategoryDtoResponse toResponse(Category category) {
        return CategoryDtoResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

package com.stefano.service.impl;

import com.stefano.dto.category.CategoryDtoRequest;
import com.stefano.dto.category.CategoryDtoResponse;
import com.stefano.mapper.CategoryMapper;
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
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryDtoResponse create(CategoryDtoRequest request) {
        return categoryMapper.toResponse(repository.save(Category.builder()
                .name(request.name())
                .build()));
    }
    @Override
    public List<CategoryDtoResponse> findAll() {
        return repository.findAll().stream().map(categoryMapper::toResponse).toList();
    }
    @Override
    public CategoryDtoResponse findById(Long id) {
        return categoryMapper.toResponse(get(id));
    }
    @Override
    public CategoryDtoResponse update(Long id, CategoryDtoRequest request) {
        Category category = get(id);
        category.setName(request.name());
        return categoryMapper.toResponse(repository.save(category));
    }
    @Override
    public void delete(Long id) {
        repository.delete(get(id));
    }

    private Category get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
    }

}

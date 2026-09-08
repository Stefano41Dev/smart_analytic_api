package com.stefano.service.impl;

import com.stefano.dto.product.ProductDtoRequest;
import com.stefano.dto.product.ProductDtoResponse;
import com.stefano.exception.ResourceNotFoundException;
import com.stefano.mapper.ProductMapper;
import com.stefano.models.Brand;
import com.stefano.models.Category;
import com.stefano.models.Product;
import com.stefano.repository.BrandRepository;
import com.stefano.repository.CategoryRepository;
import com.stefano.repository.ProductRepository;
import com.stefano.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDtoResponse create(ProductDtoRequest request) {
        Product product = productMapper.toEntity(request);

        product.setBrand(getBrand(request.brandId()));
        product.setCategory(getCategory(request.categoryId()));

        return productMapper.toResponse(repository.save(product));
    }
    @Override
    public List<ProductDtoResponse> findAll() {
        return repository.findAll().stream().map(productMapper::toResponse).toList();
    }
    @Override
    public ProductDtoResponse findById(Long id) { return productMapper.toResponse(get(id)); }
    @Override
    public ProductDtoResponse update(Long id, ProductDtoRequest request) {
        Product product = get(id);
        product.setName(request.name());
        product.setBrand(getBrand(request.brandId()));
        product.setCategory(getCategory(request.categoryId()));
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setSku(request.sku());
        return productMapper.toResponse(repository.save(product));
    }
    @Override
    public void delete(Long id) { repository.delete(get(id)); }

    private Product get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }

    private Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada: " + id));
    }
    private Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada: " + id));
    }


}

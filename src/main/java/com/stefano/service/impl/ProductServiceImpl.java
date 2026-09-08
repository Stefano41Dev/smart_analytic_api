package com.stefano.service.impl;

import com.stefano.dto.product.ProductDtoRequest;
import com.stefano.dto.product.ProductDtoResponse;
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

    public ProductDtoResponse create(ProductDtoRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public List<ProductDtoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public ProductDtoResponse findById(Long id) { return toResponse(get(id)); }

    public ProductDtoResponse update(Long id, ProductDtoRequest request) {
        Product product = get(id);
        product.setName(request.name());
        product.setBrand(getBrand(request.brandId()));
        product.setCategory(getCategory(request.categoryId()));
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setSku(request.sku());
        return toResponse(repository.save(product));
    }
    public void delete(Long id) { repository.delete(get(id)); }

    private Product toEntity(ProductDtoRequest request) {
        return Product.builder().name(request.name()).brand(getBrand(request.brandId()))
                .category(getCategory(request.categoryId())).price(request.price())
                .stock(request.stock()).sku(request.sku()).build();
    }
    private Product get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + id));
    }

    private Brand getBrand(Long id) {
        return brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marca no encontrada: " + id));
    }
    private Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + id));
    }

    private ProductDtoResponse toResponse(Product product) {
        return ProductDtoResponse.builder().id(product.getId()).name(product.getName())
                .brandId(product.getBrand().getId()).categoryId(product.getCategory().getId())
                .price(product.getPrice()).stock(product.getStock()).sku(product.getSku()).build();
    }
}

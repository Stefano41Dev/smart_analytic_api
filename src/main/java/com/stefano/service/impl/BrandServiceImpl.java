package com.stefano.service.impl;

import com.stefano.dto.brand.BrandDtoRequest;
import com.stefano.dto.brand.BrandDtoResponse;
import com.stefano.models.Brand;
import com.stefano.repository.BrandRepository;
import com.stefano.service.BrandService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository repository;
    public BrandDtoResponse create(BrandDtoRequest request) {
        return toResponse(repository.save(Brand.builder()
                .name(request.name())
                .build()));
    }
    public List<BrandDtoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    public BrandDtoResponse findById(Long id) {
        return toResponse(get(id));
    }
    public BrandDtoResponse update(Long id, BrandDtoRequest request) {
        Brand brand = get(id); brand.setName(request.name()); return toResponse(repository.save(brand));
    }
    public void delete(Long id) {
        repository.delete(get(id));
    }

    private Brand get(Long id) { return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand no encontrada: " + id)); }
    private BrandDtoResponse toResponse(Brand brand) { return BrandDtoResponse.builder()
            .id(brand.getId())
            .name(brand.getName())
            .build(); }
}

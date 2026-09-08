package com.stefano.service.impl;

import com.stefano.dto.brand.BrandDtoRequest;
import com.stefano.dto.brand.BrandDtoResponse;
import com.stefano.mapper.BrandMapper;
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
    private final BrandMapper brandMapper;

    @Override
    public BrandDtoResponse create(BrandDtoRequest request) {
        return brandMapper.toResponse(repository.save(
                Brand.builder()
                .name(request.name())
                .build()));
    }

    @Override
    public List<BrandDtoResponse> findAll() {
        return repository.findAll().stream().map(brandMapper::toResponse).toList();
    }

    @Override
    public BrandDtoResponse findById(Long id) {
        return brandMapper.toResponse(get(id));
    }

    @Override
    public BrandDtoResponse update(Long id, BrandDtoRequest request) {
        Brand brand = get(id);
        brand.setName(request.name());
        return brandMapper.toResponse(repository.save(brand));
    }

    @Override
    public void delete(Long id) {
        repository.delete(get(id));
    }

    private Brand get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Brand no encontrada: " + id));
    }

}

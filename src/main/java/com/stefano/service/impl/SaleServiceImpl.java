package com.stefano.service.impl;

import com.stefano.dto.sale.SaleDetailDtoRequest;
import com.stefano.dto.sale.SaleDtoRequest;
import com.stefano.dto.sale.SaleDtoResponse;
import com.stefano.exception.BusinessException;
import com.stefano.exception.ResourceNotFoundException;
import com.stefano.mapper.SaleMapper;
import com.stefano.models.Client;
import com.stefano.models.Product;
import com.stefano.models.Sale;
import com.stefano.models.SaleDetail;
import com.stefano.repository.ClientRepository;
import com.stefano.repository.ProductRepository;
import com.stefano.repository.SaleRepository;
import com.stefano.service.SaleService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private static final BigDecimal IGV_RATE = new BigDecimal("0.18");
    private final SaleRepository repository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final SaleMapper saleMapper;
    @Override
    public SaleDtoResponse create(SaleDtoRequest request) {
        validateDetails(request.saleDetails());
        Sale sale = Sale.builder()
                .client(getClient(request.clientId()))
                .createdAd(LocalDateTime.now())
                .subtotal(BigDecimal.ZERO)
                .igv(BigDecimal.ZERO)
                .total(BigDecimal.ZERO)
                .saleDetails(new ArrayList<>())
                .build();
        replaceDetails(sale, request.saleDetails());
        return saleMapper.toResponse(repository.save(sale));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SaleDtoResponse> findAll() { return repository.findAll().stream().map(saleMapper::toResponse).toList(); }

    @Transactional(readOnly = true)
    @Override
    public SaleDtoResponse findById(Long id) { return saleMapper.toResponse(get(id)); }
    @Override
    public SaleDtoResponse update(Long id, SaleDtoRequest request) {
        validateDetails(request.saleDetails());
        Sale sale = get(id);
        sale.setClient(getClient(request.clientId()));
        replaceDetails(sale, request.saleDetails());
        return saleMapper.toResponse(repository.save(sale));
    }

    private void replaceDetails(Sale sale, List<SaleDetailDtoRequest> requests) {
        List<SaleDetail> details = requests.stream().map(request -> createDetail(sale, request)).toList();
        sale.getSaleDetails().clear();
        sale.getSaleDetails().addAll(details);
        BigDecimal subtotal = details.stream().map(SaleDetail::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal igv = subtotal.multiply(IGV_RATE).setScale(2, RoundingMode.HALF_UP);
        sale.setSubtotal(subtotal.setScale(2, RoundingMode.HALF_UP));
        sale.setIgv(igv);
        sale.setTotal(subtotal.add(igv).setScale(2, RoundingMode.HALF_UP));
    }

    private SaleDetail createDetail(Sale sale, SaleDetailDtoRequest request) {
        Product product = getProduct(request.productId());
        BigDecimal priceUnit = product.getPrice();
        BigDecimal subtotal = priceUnit.multiply(BigDecimal.valueOf(request.quantity()))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(subtotal.multiply(IGV_RATE))
                .setScale(2, RoundingMode.HALF_UP);
        return SaleDetail.builder()
                .sale(sale)
                .product(product)
                .quantity(request.quantity())
                .priceUnit(priceUnit)
                .subtotal(subtotal)
                .total(total)
                .build();
    }

    private void validateDetails(List<SaleDetailDtoRequest> details) {
        if (details == null || details.isEmpty()) throw new BusinessException("Una venta debe tener al menos un detalle");

        if (details.stream().anyMatch(detail -> detail.productId() == null || detail.quantity() == null || detail.quantity() <= 0))
            throw new BusinessException("Cada detalle debe indicar producto y una cantidad mayor a cero");
    }

    private Sale get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada: " + id));
    }
    private Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
    }
    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
    }
}

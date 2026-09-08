package com.stefano.controller;

import com.stefano.dto.sale.SaleDtoRequest;
import com.stefano.dto.sale.SaleDtoResponse;
import com.stefano.service.SaleService;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleDtoResponse> create(@RequestBody SaleDtoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<SaleDtoResponse>> findAll() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDtoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDtoResponse> update(@PathVariable Long id, @RequestBody SaleDtoRequest request) {
        return ResponseEntity.ok(saleService.update(id, request));
    }
}

package com.stefano.repository;

import com.stefano.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT SUM(s.total) FROM Sale s")
    BigDecimal sumTotal();

    @Query("SELECT COUNT(sd.quantity) FROM SaleDetail sd")
    Long totalProductsSold();

    @Query("SELECT AVG(s.total) FROM Sale s")
    BigDecimal averageSale();
}

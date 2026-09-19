package com.stefano.service.impl;

import com.stefano.ia.IAProvider;
import com.stefano.models.dto.sale.SaleSumary;
import com.stefano.service.AnalyticService;
import com.stefano.service.IAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAServiceImpl implements IAService {

    private final IAProvider iaProvider;
    private final AnalyticService analyticService;

    @Override
    public String ask(String question) {
        return iaProvider.generateResponse(question);
    }

    @Override
    public String analyze(String question) {
        SaleSumary saleSumary = analyticService.getSummary();

        String prompt = """
        Eres un analista de ventas.

        Estos son los datos de la empresa:

        Ingresos totales: %s
        Número de ventas: %s
        Productos vendidos: %s
        Venta promedio: %s

        El usuario pregunta:
        %s

        Analiza los datos y responde la pregunta.
        No inventes información.
        """.formatted(
                saleSumary.totalRevenue(),
                saleSumary.totalSales(),
                saleSumary.totalProductsSold(),
                saleSumary.averageSale(),
                question
        );

        return iaProvider.generateResponse(prompt);
    }
}

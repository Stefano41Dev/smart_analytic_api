package com.stefano.service.impl;

import com.stefano.ia.IAProvider;
import com.stefano.service.IAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IAServiceImpl implements IAService {

    private final IAProvider iaProvider;

    @Override
    public String ask(String question) {
        return iaProvider.generateResponse(question);
    }

    @Override
    public String analyze(String prompt) {
        return "";
    }
}

package com.stefano.ia;

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
}

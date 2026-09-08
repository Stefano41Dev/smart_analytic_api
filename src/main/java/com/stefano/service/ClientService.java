package com.stefano.service;

import com.stefano.dto.client.ClientDtoRequest;
import com.stefano.dto.client.ClientDtoResponse;
import java.util.List;

public interface ClientService {
    ClientDtoResponse create(ClientDtoRequest request);
    List<ClientDtoResponse> findAll();
    ClientDtoResponse findById(Long id);
    ClientDtoResponse update(Long id, ClientDtoRequest request);
    void delete(Long id);
}

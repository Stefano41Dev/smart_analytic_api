package com.stefano.service.impl;

import com.stefano.dto.client.ClientDtoRequest;
import com.stefano.dto.client.ClientDtoResponse;
import com.stefano.models.Client;
import com.stefano.repository.ClientRepository;
import com.stefano.service.ClientService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    public ClientDtoResponse create(ClientDtoRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }
    public List<ClientDtoResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }
    public ClientDtoResponse findById(Long id) {
        return toResponse(get(id));
    }
    public ClientDtoResponse update(Long id, ClientDtoRequest request) {
        Client client = get(id);
        client.setNames(request.names());
        client.setLastnames(request.lastnames());
        client.setDni(request.dni());
        client.setGmail(request.gmail());
        return toResponse(repository.save(client));
    }
    public void delete(Long id) {
        repository.delete(get(id));
    }
    private Client get(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + id));
    }
    private Client toEntity(ClientDtoRequest request) {
        return Client.builder()
                .names(request.names())
                .lastnames(request.lastnames())
                .dni(request.dni())
                .gmail(request.gmail())
                .build();
    }
    private ClientDtoResponse toResponse(Client client) {
        return ClientDtoResponse.builder()
                .id(client.getId())
                .names(client.getNames())
                .lastnames(client.getLastnames())
                .dni(client.getDni())
                .gmail(client.getGmail())
                .build();
    }
}

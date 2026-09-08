package com.stefano.service.impl;

import com.stefano.dto.client.ClientDtoRequest;
import com.stefano.dto.client.ClientDtoResponse;
import com.stefano.exception.ResourceNotFoundException;
import com.stefano.mapper.ClientMapper;
import com.stefano.models.Client;
import com.stefano.repository.ClientRepository;
import com.stefano.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDtoResponse create(ClientDtoRequest request) {
        return clientMapper.toResponse(repository.save(clientMapper.toEntity(request)));
    }
    @Override
    public List<ClientDtoResponse> findAll() {
        return repository.findAll().stream().map(clientMapper::toResponse).toList();
    }
    @Override
    public ClientDtoResponse findById(Long id) {
        return clientMapper.toResponse(get(id));
    }
    @Override
    public ClientDtoResponse update(Long id, ClientDtoRequest request) {
        Client client = get(id);
        client.setNames(request.names());
        client.setLastnames(request.lastnames());
        client.setDni(request.dni());
        client.setGmail(request.gmail());
        return clientMapper.toResponse(repository.save(client));
    }
    @Override
    public void delete(Long id) {
        repository.delete(get(id));
    }

    private Client get(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado: " + id));
    }


}

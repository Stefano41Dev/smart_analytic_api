package com.stefano.mapper;

import com.stefano.dto.client.ClientDtoRequest;
import com.stefano.dto.client.ClientDtoResponse;
import com.stefano.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDtoResponse toResponse(Client client) {
        return ClientDtoResponse.builder()
                .id(client.getId())
                .names(client.getNames())
                .lastnames(client.getLastnames())
                .dni(client.getDni())
                .gmail(client.getGmail())
                .build();
    }
    public Client toEntity(ClientDtoRequest request) {
        return Client.builder()
                .names(request.names())
                .lastnames(request.lastnames())
                .dni(request.dni())
                .gmail(request.gmail())
                .build();
    }
}

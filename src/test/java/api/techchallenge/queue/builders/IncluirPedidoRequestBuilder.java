package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;

import java.util.UUID;

public class IncluirPedidoRequestBuilder {
    public static IncluirPedidoRequest build(UUID id){
        return IncluirPedidoRequest.builder()
                .id(id)
                .build();
    }

    public static IncluirPedidoRequest build(){
        return build(UUID.randomUUID());
    }
}

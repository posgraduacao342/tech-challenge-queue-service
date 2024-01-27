package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.entities.Item;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class IncluirPedidoRequestBuilder {
    public static IncluirPedidoRequest build(){
        List<Item> itens = Arrays.asList(
                ItemBuilder.build(),
                ItemBuilder.build()
        );
        return IncluirPedidoRequest.builder()
                .id(UUID.randomUUID())
                .itens(itens)
                .build();
    }
}

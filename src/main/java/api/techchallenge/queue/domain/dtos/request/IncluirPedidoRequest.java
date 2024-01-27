package api.techchallenge.queue.domain.dtos.request;

import api.techchallenge.queue.domain.entities.Item;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class IncluirPedidoRequest {
    private UUID id;
    private List<Item> itens;
}

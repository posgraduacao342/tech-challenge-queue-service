package api.techchallenge.queue.domain.dtos.request;

import api.techchallenge.queue.domain.entities.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class IncluirPedidoRequest {
    private UUID id;
    private List<Item> itens;
}

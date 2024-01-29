package api.techchallenge.queue.domain.dtos.request;

import api.techchallenge.queue.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AtualizarStatusPedidoRequest {
    private UUID id;
    private StatusPedido status;
}

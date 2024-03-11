package api.techchallenge.queue.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class IncluirPedidoRequest {
    private UUID id;
    private UUID idCliente;
}

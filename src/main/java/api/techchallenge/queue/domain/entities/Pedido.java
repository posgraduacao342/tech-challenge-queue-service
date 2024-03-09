package api.techchallenge.queue.domain.entities;

import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.enums.StatusPedido;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class Pedido {
    private UUID id;
    private UUID clienteId;
    private LocalDateTime dataRecebimento;
    private StatusPedido status;
    private Long posicaoFila;

    public Pedido(UUID id, UUID clienteId, LocalDateTime dataRecebimento, StatusPedido status, Long posicaoFila){
        this.id = id;
        this.clienteId = clienteId;
        this.dataRecebimento = dataRecebimento;
        this.status = status;
        this.posicaoFila = posicaoFila;
    }

    public Pedido(IncluirPedidoRequest request){
        id = request.getId();
        clienteId = request.getIdCliente();
        dataRecebimento = LocalDateTime.now();
        status = StatusPedido.RECEBIDO;
    }
}
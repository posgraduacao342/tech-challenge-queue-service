package api.techchallenge.queue.domain.entities;

import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.enums.StatusPedido;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Pedido {
    private UUID id;
    private LocalDateTime dataRecebimento;
    private List<Item> itens;
    private StatusPedido status;
    private Long posicaoFila;

    public Pedido(UUID id, LocalDateTime dataRecebimento, List<Item> itens, StatusPedido status, Long posicaoFila){
        this.id = id;
        this.dataRecebimento = dataRecebimento;
        this.itens = itens;
        this.status = status;
        this.posicaoFila = posicaoFila;
    }

    public Pedido(IncluirPedidoRequest request){
        id = request.getId();
        dataRecebimento = LocalDateTime.now();
        itens = request.getItens();
        status = StatusPedido.RECEBIDO;
    }
}
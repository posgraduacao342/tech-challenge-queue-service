package api.techchallenge.queue.infrastructure.entities;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.enums.StatusPedido;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class PedidoMongoEntity {
    @MongoId
    private UUID id;
    private UUID clienteId;
    private LocalDateTime dataRecebimento;
    private StatusPedido status;
    private Long posicaoFila;

    public PedidoMongoEntity(Pedido pedido){
        id = pedido.getId();
        clienteId = pedido.getClienteId();
        dataRecebimento = pedido.getDataRecebimento();
        status = pedido.getStatus();
        posicaoFila = pedido.getPosicaoFila();
    }

    public Pedido toDomain(){
        return new Pedido(id, clienteId,dataRecebimento, status, posicaoFila);
    }
}
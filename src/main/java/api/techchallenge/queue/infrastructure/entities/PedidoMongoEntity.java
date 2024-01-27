package api.techchallenge.queue.infrastructure.entities;

import api.techchallenge.queue.domain.entities.Item;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoMongoEntity {
    @MongoId
    private UUID id;
    private LocalDateTime dataRecebimento;
    private List<Item> itens;
    private StatusPedido status;
    private Long posicaoFila;

    public PedidoMongoEntity(Pedido pedido){
        id = pedido.getId();
        dataRecebimento = pedido.getDataRecebimento();
        itens = pedido.getItens();
        status = pedido.getStatus();
        posicaoFila = pedido.getPosicaoFila();
    }

    public Pedido toDomain(){
        return new Pedido(id, dataRecebimento, itens, status, posicaoFila);
    }
}
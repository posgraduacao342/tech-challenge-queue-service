package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PedidoMongoEntityBuilder {
    public static PedidoMongoEntity build(long posicaoFila){
        return PedidoMongoEntity.builder()
                .id(UUID.randomUUID())
                .itens(ItemBuilder.buildList())
                .dataRecebimento(LocalDateTime.now())
                .posicaoFila(posicaoFila)
                .status(StatusPedido.EM_PREPARACAO)
                .build();
    }

    public static PedidoMongoEntity build(){
        return build(1);
    }

    public static List<PedidoMongoEntity> buildList(){
        return Arrays.asList(
                build(1),
                build(2)
        );
    }
}

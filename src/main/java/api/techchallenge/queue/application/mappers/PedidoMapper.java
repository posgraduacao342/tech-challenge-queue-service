package api.techchallenge.queue.application.mappers;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;

import java.util.List;

public class PedidoMapper {
    private PedidoMapper(){}
    public static List<Pedido> toDomainList(List<PedidoMongoEntity> pedidoEntities){
        return pedidoEntities.stream()
            .map(PedidoMongoEntity::toDomain)
            .toList();
    }
}

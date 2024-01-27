package api.techchallenge.queue.application.mappers;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {
    private PedidoMapper(){}
    public static List<Pedido> toDomainList(List<PedidoMongoEntity> pedidoEntities){
        return pedidoEntities.stream()
            .map(PedidoMongoEntity::toDomain)
            .collect(Collectors.toList());
    }
}

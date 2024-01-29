package api.techchallenge.queue.infrastructure.repositories;

import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoMongoEntity, UUID> {
    List<PedidoMongoEntity> findAllByStatusInAndDataRecebimentoBetweenOrderByPosicaoFila(List<StatusPedido> status,LocalDateTime dataInicial, LocalDateTime dataFinal);
    Optional<PedidoMongoEntity> findTopByDataRecebimentoBetweenOrderByPosicaoFila(LocalDateTime dataInicial, LocalDateTime dataFinal);
}
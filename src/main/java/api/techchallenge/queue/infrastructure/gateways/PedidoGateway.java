package api.techchallenge.queue.infrastructure.gateways;

import api.techchallenge.queue.application.mappers.PedidoMapper;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.exceptions.NotFoundException;
import api.techchallenge.queue.domain.ports.PedidoGatewayPort;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;
import api.techchallenge.queue.infrastructure.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class PedidoGateway implements PedidoGatewayPort {
    private final PedidoRepository repository;

    @Autowired
    public PedidoGateway(PedidoRepository repository) {
        this.repository = repository;
    }
    public List<Pedido> buscarPedidos(){
        return PedidoMapper.toDomainList(
            repository.findAll()
        );
    }

    public List<Pedido> buscarPedidoPorStatus(List<StatusPedido> status, LocalDate data){
        var dataInicial = data.atTime(0,0,0);
        var dataFinal = data.atTime(0,0,0).plusDays(1);
        return PedidoMapper.toDomainList(
                repository.findAllByStatusInAndDataRecebimentoBetweenOrderByPosicaoFila(status, dataInicial, dataFinal)
        );
    }

    public void incluirPedido(Pedido pedido) {
        repository.save(new PedidoMongoEntity(pedido));
    }

    public void atualizarStatusPedido(UUID id, StatusPedido status) throws NotFoundException {
        var pedido = repository.findById(id);
        if (pedido.isEmpty()){
            throw new NotFoundException("Pedido não encontrado");
        }
        pedido.get().setStatus(status);
        repository.save(pedido.get());
    }

    public boolean pedidoExiste(UUID id){
        return repository.existsById(id);
    }

    public Long ultimaPosicaoFilaPorData(LocalDate data) {
        var dataInicial = data.atTime(0,0,0);
        var dataFinal = data.atTime(0,0,0).plusDays(1);
        var pedido = repository.findTopByDataRecebimentoBetweenOrderByPosicaoFila(dataInicial, dataFinal);
        return pedido.isPresent() ? pedido.get().getPosicaoFila() : 0;
    }
}
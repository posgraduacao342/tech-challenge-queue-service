package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.ports.UseCaseOnlyRequestPort;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;

public class AtualizarStatusPedidoUseCase implements UseCaseOnlyRequestPort<AtualizarStatusPedidoRequest> {
    private final PedidoGateway gateway;

    @Autowired
    public AtualizarStatusPedidoUseCase(PedidoGateway gateway){
        this.gateway = gateway;
    }

    public UseCaseResponseNoData execute(AtualizarStatusPedidoRequest request) {
        gateway.atualizarStatusPedido(request.getId(), request.getStatus());
        return new UseCaseResponseNoData();
    }
}

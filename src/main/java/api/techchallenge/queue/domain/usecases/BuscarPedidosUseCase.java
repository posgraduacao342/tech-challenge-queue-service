package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.ports.UseCaseOnlyResponsePort;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BuscarPedidosUseCase implements UseCaseOnlyResponsePort<List<Pedido>> {
    private final PedidoGateway gateway;

    @Autowired
    public BuscarPedidosUseCase(PedidoGateway gateway){
        this.gateway = gateway;
    }

    public UseCaseResponse<List<Pedido>> execute(){
        return new UseCaseResponse<>(gateway.buscarPedidos());
    }
}

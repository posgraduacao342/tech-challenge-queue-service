package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.ports.UseCaseOnlyResponsePort;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public class BuscarFilaDePedidosUseCase implements UseCaseOnlyResponsePort<List<Pedido>> {
    private final PedidoGateway gateway;

    @Autowired
    public BuscarFilaDePedidosUseCase(PedidoGateway gateway){
        this.gateway = gateway;
    }

    public UseCaseResponse<List<Pedido>> execute(){
        return new UseCaseResponse<>(gateway.buscarPedidoPorStatus(StatusPedido.statusAtivos(), LocalDate.now()));
    }
}

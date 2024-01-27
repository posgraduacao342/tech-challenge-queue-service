package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.dtos.response.IncluirPedidoResponse;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.ports.UseCaseOnlyRequestPort;
import api.techchallenge.queue.domain.ports.UseCasePort;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class IncluirPedidoUseCase implements UseCasePort<IncluirPedidoResponse, IncluirPedidoRequest> {
    private final PedidoGateway gateway;

    @Autowired
    public IncluirPedidoUseCase(PedidoGateway gateway){
        this.gateway = gateway;
    }

    public UseCaseResponse<IncluirPedidoResponse> execute(IncluirPedidoRequest request){
        if (gateway.pedidoExiste(request.getId())){
            return new UseCaseResponse<>(HttpStatus.BAD_REQUEST, "Pedido já existe.");
        }
        var pedido = new Pedido(request);
        var posicaoFila = gateway.ultimaPosicaoFilaPorData(pedido.getDataRecebimento().toLocalDate()) + 1;
        pedido.setPosicaoFila(posicaoFila);

        gateway.incluirPedido(pedido);
        return new UseCaseResponse<>(new IncluirPedidoResponse(posicaoFila));
    }
}

package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.ports.ClienteGatewayPort;
import api.techchallenge.queue.domain.ports.UseCaseOnlyRequestPort;
import api.techchallenge.queue.domain.ports.out.EnviarEmailQueueOUTPort;
import api.techchallenge.queue.infrastructure.gateways.database.PedidoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtualizarStatusPedidoUseCase implements UseCaseOnlyRequestPort<AtualizarStatusPedidoRequest> {
    private final PedidoGateway gateway;
    private final EnviarEmailQueueOUTPort sendEmailQueueGateway;
    private final ClienteGatewayPort clienteGateway;

    @Autowired
    public AtualizarStatusPedidoUseCase(PedidoGateway gateway, EnviarEmailQueueOUTPort sendEmailQueueGateway, ClienteGatewayPort clienteGateway) {
        this.gateway = gateway;
        this.sendEmailQueueGateway = sendEmailQueueGateway;
        this.clienteGateway = clienteGateway;
    }

    @Transactional
    public UseCaseResponseNoData execute(AtualizarStatusPedidoRequest request) {
        gateway.atualizarStatusPedido(request.getId(), request.getStatus());

        var pedido = gateway.buscarPorId(request.getId());

        if (pedido.getClienteId() != null){
            clienteGateway.buscarClientePorId(pedido.getClienteId())
                .subscribe(
                    cliente -> {
                        if(!cliente.getEmail().isEmpty()){
                            String assunto;
                            String texto;
                            switch(request.getStatus()){
                                case EM_PREPARACAO:
                                    assunto = "Seu pedido está em preparação";
                                    texto = "Seu pedido iniciou a preparação, em breve ele estará disponível para retirada!";
                                    break;
                                case PRONTO:
                                    assunto = "Seu pedido está pronto!";
                                    texto = "Seu pedido está pronto para retirada, bom apetite!";
                                    break;
                                default:
                                    return;
                            }

                            sendEmailQueueGateway.publish(cliente.getEmail(), assunto, texto);
                        }
                    }
                );
        }
        return new UseCaseResponseNoData();
    }
}

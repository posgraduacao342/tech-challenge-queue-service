package api.techchallenge.queue.application.config;

import api.techchallenge.queue.domain.ports.ClienteGatewayPort;
import api.techchallenge.queue.domain.ports.out.EnviarEmailQueueOUTPort;
import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import api.techchallenge.queue.domain.usecases.BuscarFilaDePedidosUseCase;
import api.techchallenge.queue.domain.usecases.BuscarPedidosUseCase;
import api.techchallenge.queue.domain.usecases.IncluirPedidoUseCase;
import api.techchallenge.queue.infrastructure.gateways.database.PedidoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public BuscarPedidosUseCase buscarPedidosUseCaseConfig(PedidoGateway pedidoGateway){
        return new BuscarPedidosUseCase(pedidoGateway);
    }
    @Bean
    public BuscarFilaDePedidosUseCase buscarPedidosEmPreparacaoUseCaseConfig(PedidoGateway pedidoGateway){
        return new BuscarFilaDePedidosUseCase(pedidoGateway);
    }
    @Bean
    public IncluirPedidoUseCase incluirPedidoUseCaseConfig(PedidoGateway pedidoGateway){
        return new IncluirPedidoUseCase(pedidoGateway);
    }
    @Bean
    public AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCaseConfig(PedidoGateway gateway, EnviarEmailQueueOUTPort sendEmailQueueGateway, ClienteGatewayPort clienteGateway){
        return new AtualizarStatusPedidoUseCase(gateway, sendEmailQueueGateway, clienteGateway);
    }
}
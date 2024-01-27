package api.techchallenge.queue.application.config;

import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import api.techchallenge.queue.domain.usecases.BuscarFilaDePedidosUseCase;
import api.techchallenge.queue.domain.usecases.BuscarPedidosUseCase;
import api.techchallenge.queue.domain.usecases.IncluirPedidoUseCase;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
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
    public AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCaseConfig(PedidoGateway pedidoGateway){
        return new AtualizarStatusPedidoUseCase(pedidoGateway);
    }
}
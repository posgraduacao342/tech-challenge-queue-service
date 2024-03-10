package api.techchallenge.queue.unittests.domain.usecases;
import api.techchallenge.queue.builders.ClienteBuilder;
import api.techchallenge.queue.builders.PedidoBuilder;
import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.ports.out.EnviarEmailQueueOUTPort;
import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import api.techchallenge.queue.infrastructure.gateways.api.ClienteGateway;
import api.techchallenge.queue.infrastructure.gateways.database.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtualizarStatusPedidoUseCaseTest {
    @Mock
    private PedidoGateway gateway;
    @Mock
    private ClienteGateway clienteGateway;
    @Mock
    private EnviarEmailQueueOUTPort sendEmailQueueGateway;

    @InjectMocks
    private AtualizarStatusPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @EnumSource(StatusPedido.class)
    void testExecute(StatusPedido statusPedido) {
        // Arrange
        var request = new AtualizarStatusPedidoRequest(UUID.randomUUID(), StatusPedido.FINALIZADO);
        var pedido = PedidoBuilder.build();
        pedido.setId(request.getId());
        pedido.setStatus(statusPedido);
        Mockito.doNothing().when(gateway).atualizarStatusPedido(request.getId(), request.getStatus());
        Mockito.when(gateway.buscarPorId(pedido.getId())).thenReturn(pedido);
        var cliente = ClienteBuilder.build();
        cliente.setId(pedido.getClienteId());
        Mockito.when(clienteGateway.buscarClientePorId(cliente.getId())).thenReturn(Mono.just(cliente));
        Mockito.doNothing().when(sendEmailQueueGateway).publish(Mockito.any(),Mockito.any(),Mockito.any());

        // Act
        UseCaseResponseNoData response = useCase.execute(request);

        // Assert
        assertNotNull(response);
        Mockito.verify(gateway, Mockito.times(1)).atualizarStatusPedido(Mockito.any(), Mockito.any());
    }
}
package api.techchallenge.queue.unittests.domain.usecases;

import api.techchallenge.queue.builders.PedidoBuilder;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.usecases.BuscarFilaDePedidosUseCase;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuscarFilaDePedidosUseCaseTest {

    @Mock
    private PedidoGateway gateway;

    @InjectMocks
    private BuscarFilaDePedidosUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        var pedidos = PedidoBuilder.buildList();
        Mockito.when(gateway.buscarPedidoPorStatus(Mockito.any(), Mockito.any())).thenReturn(pedidos);

        // Act
        UseCaseResponse<List<Pedido>> response = useCase.execute();

        // Assert
        assertNotNull(response);
        assertEquals(pedidos, response.getData());
        Mockito.verify(gateway, Mockito.times(1)).buscarPedidoPorStatus(Mockito.any(), Mockito.any());
    }
}
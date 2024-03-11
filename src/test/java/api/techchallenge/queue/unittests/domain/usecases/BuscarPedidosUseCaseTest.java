package api.techchallenge.queue.unittests.domain.usecases;

import api.techchallenge.queue.builders.PedidoBuilder;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.usecases.BuscarPedidosUseCase;
import api.techchallenge.queue.infrastructure.gateways.database.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BuscarPedidosUseCaseTest {

    @Mock
    private PedidoGateway gateway;

    @InjectMocks
    private BuscarPedidosUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        var pedidos = PedidoBuilder.buildList();
        Mockito.when(gateway.buscarPedidos()).thenReturn(pedidos);

        // Act
        UseCaseResponse<List<Pedido>> response = useCase.execute();

        // Assert
        assertNotNull(response);
        assertEquals(pedidos, response.getData());
        Mockito.verify(gateway, Mockito.times(1)).buscarPedidos();
    }
}
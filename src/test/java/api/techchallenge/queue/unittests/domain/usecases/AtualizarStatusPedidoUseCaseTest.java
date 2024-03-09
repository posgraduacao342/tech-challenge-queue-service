package api.techchallenge.queue.unittests.domain.usecases;
import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import api.techchallenge.queue.infrastructure.gateways.database.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtualizarStatusPedidoUseCaseTest {
    @Mock
    private PedidoGateway gateway;

    @InjectMocks
    private AtualizarStatusPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        var request = new AtualizarStatusPedidoRequest(UUID.randomUUID(), StatusPedido.FINALIZADO);
        Mockito.doNothing().when(gateway).atualizarStatusPedido(request.getId(), request.getStatus());

        // Act
        UseCaseResponseNoData response = useCase.execute(request);

        // Assert
        assertNotNull(response);
        Mockito.verify(gateway, Mockito.times(1)).atualizarStatusPedido(Mockito.any(), Mockito.any());
    }
}
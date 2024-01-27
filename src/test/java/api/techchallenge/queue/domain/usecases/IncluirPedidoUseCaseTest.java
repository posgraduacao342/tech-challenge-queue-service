package api.techchallenge.queue.domain.usecases;

import api.techchallenge.queue.builders.IncluirPedidoRequestBuilder;
import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.dtos.response.IncluirPedidoResponse;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IncluirPedidoUseCaseTest {

    @Mock
    private PedidoGateway gateway;

    @InjectMocks
    private IncluirPedidoUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_returnSuccess() {
        // Arrange
        IncluirPedidoRequest request = IncluirPedidoRequestBuilder.build();

        Mockito.when(gateway.pedidoExiste(Mockito.any())).thenReturn(false);
        Mockito.when(gateway.ultimaPosicaoFilaPorData(Mockito.any())).thenReturn((long)1);

        // Act
        UseCaseResponse<IncluirPedidoResponse> response = useCase.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(2, response.getData().posicaoFila);
        Mockito.verify(gateway, Mockito.times(1)).incluirPedido(Mockito.any());
    }

    @Test
    void testExecute_whenPedidoExists_returnError() {
        // Arrange
        IncluirPedidoRequest request = IncluirPedidoRequestBuilder.build();
        Mockito.when(gateway.pedidoExiste(Mockito.any())).thenReturn(true);

        // Act
        UseCaseResponse<IncluirPedidoResponse> response = useCase.execute(request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
        assertEquals("Pedido já existe.", response.getErrorMessage());
        Mockito.verify(gateway, Mockito.never()).incluirPedido(Mockito.any());
    }
}
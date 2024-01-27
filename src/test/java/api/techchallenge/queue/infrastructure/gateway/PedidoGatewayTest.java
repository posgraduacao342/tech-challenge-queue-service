package api.techchallenge.queue.infrastructure.gateway;

import api.techchallenge.queue.builders.PedidoBuilder;
import api.techchallenge.queue.builders.PedidoMongoEntityBuilder;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.exceptions.NotFoundException;
import api.techchallenge.queue.infrastructure.entities.PedidoMongoEntity;
import api.techchallenge.queue.infrastructure.gateways.PedidoGateway;
import api.techchallenge.queue.infrastructure.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class PedidoGatewayTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private PedidoGateway gateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPedidos_returnSuccessResult() {
        // Arrange
        var pedidos = PedidoMongoEntityBuilder.buildList();
        Mockito.when(repository.findAll())
            .thenReturn(pedidos);

        // Act
        var result = gateway.buscarPedidos();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(pedidos.size(), result.size());
        assertEquals(pedidos.stream().findFirst().get().getId(), result.stream().findFirst().get().getId());
    }

    @Test
    void testBuscarPedidoPorStatus_returnSuccessResult() {
        // Arrange
        var status = Arrays.asList(StatusPedido.RECEBIDO, StatusPedido.EM_PREPARACAO, StatusPedido.PRONTO);
        var data = LocalDate.of(2024, 1, 24);
        var pedidoEntities = PedidoMongoEntityBuilder.buildList();
        Mockito.when(repository.findAllByStatusInAndDataRecebimentoBetweenOrderByPosicaoFila(status,
                        LocalDateTime.of(2024, 1, 24,0,0,0,0),
                        LocalDateTime.of(2024, 1, 25,0,0,0,0)))
                .thenReturn(pedidoEntities);

        // Act
        var result = gateway.buscarPedidoPorStatus(status, data);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(pedidoEntities.size(), result.size());
        assertEquals(pedidoEntities.stream().findFirst().get().getId(), result.stream().findFirst().get().getId());
    }

    @Test
    void testIncluirPedido_returnSuccessResult() {
        // Arrange
        var pedido = PedidoBuilder.build();
        var pedidoEntity = new PedidoMongoEntity(pedido);
        Mockito.when(repository.save(pedidoEntity))
                .thenReturn(pedidoEntity);

        // Act
        gateway.incluirPedido(pedido);

        // Assert
        Mockito.verify(repository, times(1)).save(pedidoEntity);
    }

    @Test
    void testAtualizarStatusPedido_returnSuccessResult() {
        // Arrange
        var pedido = PedidoMongoEntityBuilder.build();
        var status = StatusPedido.FINALIZADO;
        Mockito.when(repository.findById(pedido.getId())).thenReturn(Optional.of(pedido));

        // Act
        assertDoesNotThrow(() -> gateway.atualizarStatusPedido(pedido.getId(), status));

        // Assert
        Mockito.verify(repository, times(1)).save(pedido);
    }

    @Test
    void testAtualizarStatusPedido_whenPedidoNotFound_throwsNotFoundException() {
        // Arrange
        UUID id = UUID.randomUUID();
        StatusPedido status = StatusPedido.FINALIZADO;
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> gateway.atualizarStatusPedido(id, status));
    }

    @Test
    void testPedidoExiste_returnSuccessResult() {
        // Arrange
        var id = UUID.randomUUID();
        Mockito.when(repository.existsById(id)).thenReturn(true);

        // Act
        boolean result = gateway.pedidoExiste(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void testUltimaPosicaoFilaPorData_returnSuccessResult() {
        // Arrange
        var data = LocalDate.of(2024, 1, 24);
        var pedido = PedidoMongoEntityBuilder.build();
        Mockito.when(repository.findTopByDataRecebimentoBetweenOrderByPosicaoFila(
                    LocalDateTime.of(2024, 1, 24,0,0,0,0),
                    LocalDateTime.of(2024, 1, 25,0,0,0,0))
                )
                .thenReturn(Optional.of(pedido));

        // Act
        Long result = gateway.ultimaPosicaoFilaPorData(data);

        // Assert
        assertNotNull(result);
        assertEquals(pedido.getPosicaoFila(), result);
    }

    @Test
    void testUltimaPosicaoFilaPorData_returnSuccessResult0() {
        // Arrange
        var data = LocalDate.of(2024, 1, 24);
        Mockito.when(repository.findTopByDataRecebimentoBetweenOrderByPosicaoFila(
                        LocalDateTime.of(2024, 1, 24,0,0,0,0),
                        LocalDateTime.of(2024, 1, 25,0,0,0,0))
                )
                .thenReturn(Optional.empty());

        // Act
        Long result = gateway.ultimaPosicaoFilaPorData(data);

        // Assert
        assertNotNull(result);
        assertEquals(0, result);
    }
}
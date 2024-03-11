package api.techchallenge.queue.unittests.infrastructure.gateway;

import api.techchallenge.queue.builders.ClienteBuilder;
import api.techchallenge.queue.domain.entities.Cliente;
import api.techchallenge.queue.infrastructure.gateways.api.ClienteGateway;
import api.techchallenge.queue.infrastructure.proxies.HttpProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ClienteGatewayTest {
    @Mock
    private HttpProxy<Cliente> httpProxy;
    @InjectMocks
    private ClienteGateway clienteGateway;

    @Test
    void testBuscarClientePorId() {
        // Arrange
        Cliente cliente = ClienteBuilder.build();
        Mockito.when(httpProxy.get(Mockito.any())).thenReturn(Mono.just(cliente));

        // Act
        Mono<Cliente> resultado = clienteGateway.buscarClientePorId(cliente.getId());

        // Assert
        Assertions.assertNotNull(resultado);

        var clienteResultado = resultado.block();
        Assertions.assertEquals(cliente, clienteResultado);

        Mockito.verify(httpProxy).get("clientes/id/" + cliente.getId());
        Mockito.verify(httpProxy, Mockito.times(1)).get(Mockito.anyString());
    }
}

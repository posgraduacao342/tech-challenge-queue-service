package api.techchallenge.queue.domain.ports;

import api.techchallenge.queue.domain.entities.Cliente;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ClienteGatewayPort {
    Mono<Cliente> buscarClientePorId(UUID idCliente);
}

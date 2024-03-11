package api.techchallenge.queue.infrastructure.gateways.api;

import api.techchallenge.queue.domain.entities.Cliente;
import api.techchallenge.queue.domain.ports.ClienteGatewayPort;
import api.techchallenge.queue.infrastructure.proxies.HttpProxy;1
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.util.UUID;

@Component
public class ClienteGateway implements ClienteGatewayPort {
    private final HttpProxy<Cliente> httpProxy;

    @Autowired
    public ClienteGateway(@Value("${http.source.account}") String baseUri) {
        this.httpProxy = new HttpProxy<>(Cliente.class, baseUri);
    }

    @Override
    public Mono<Cliente> buscarClientePorId(UUID idCliente) {
        return this.httpProxy
                .get(MessageFormat.format("clientes/id/{0}", idCliente));
    }
}

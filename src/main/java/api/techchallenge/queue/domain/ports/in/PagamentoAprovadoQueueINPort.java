package api.techchallenge.queue.domain.ports.in;

import org.springframework.messaging.handler.annotation.Payload;

public interface PagamentoAprovadoQueueINPort {
    void receive(@Payload String message);
}

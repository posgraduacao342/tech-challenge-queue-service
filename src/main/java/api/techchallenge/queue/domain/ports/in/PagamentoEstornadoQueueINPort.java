package api.techchallenge.queue.domain.ports.in;

import org.springframework.messaging.handler.annotation.Payload;

public interface PagamentoEstornadoQueueINPort {
    void receive(@Payload String message);
}

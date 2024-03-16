package api.techchallenge.queue.infrastructure.messaging.in;

import api.techchallenge.queue.application.config.RabbitMQConfig;
import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.ports.in.PagamentoAprovadoQueueINPort;
import api.techchallenge.queue.domain.usecases.IncluirPedidoUseCase;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class PagamentoAprovadoQueueIN implements PagamentoAprovadoQueueINPort {

    private IncluirPedidoUseCase incluirPedidoUseCase;
    private Logger logger;

    @Autowired
    public PagamentoAprovadoQueueIN(IncluirPedidoUseCase incluirPedidoUseCase){
        this.incluirPedidoUseCase = incluirPedidoUseCase;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    private final Gson gson = new Gson();

    @RabbitListener(queues = RabbitMQConfig.PAGAMENTO_APROVADO_QUEUE)
    @Override
    public void receive(@Payload String message){
        try {
            HashMap<String, String> hashMap = gson.fromJson(message, HashMap.class);

            var pedidoId = UUID.fromString(hashMap.get("pedidoId"));

            UUID clienteId = null;
            if(hashMap.containsKey("clienteId")){
                clienteId = UUID.fromString(hashMap.get("clienteId"));
            }

            var request = new IncluirPedidoRequest(pedidoId, clienteId);
            incluirPedidoUseCase.execute(request);
        }
        catch(Exception ex) {
            var errorMessage = "Houve um problema ao receber o pagamento aprovado: " + ex.getMessage();

            logger.error(errorMessage, ex);
            throw new AmqpRejectAndDontRequeueException(errorMessage);
        }
    }
}

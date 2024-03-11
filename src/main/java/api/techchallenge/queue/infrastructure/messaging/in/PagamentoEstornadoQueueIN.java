package api.techchallenge.queue.infrastructure.messaging.in;

import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.exceptions.NotFoundException;
import api.techchallenge.queue.domain.ports.in.PagamentoAprovadoQueueINPort;
import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.UUID;

@Service
public class PagamentoEstornadoQueueIN implements PagamentoAprovadoQueueINPort {

    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private Logger logger;

    @Autowired
    public PagamentoEstornadoQueueIN(AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase){
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    private final Gson gson = new Gson();

    @RabbitListener(queues = "queue_pagamento_estornado")
    @Override
    public void receive(@Payload String message) throws InvalidParameterException, NotFoundException {
        try {
            HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);

            var id = UUID.fromString(mensagem.get("pedidoId"));
            var request = new AtualizarStatusPedidoRequest(id, StatusPedido.CANCELADO);
            atualizarStatusPedidoUseCase.execute(request);
        }
        catch(Exception ex) {
            var errorMessage = "Houve um problema ao receber o pagamento aprovado: " + ex.getMessage();

            logger.error(errorMessage, ex);
            throw new AmqpRejectAndDontRequeueException(errorMessage);
        }
    }
}

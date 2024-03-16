package api.techchallenge.queue.infrastructure.messaging.out;

import api.techchallenge.queue.application.config.RabbitMQConfig;
import api.techchallenge.queue.domain.ports.out.EnviarEmailQueueOUTPort;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class EnviarEmailQueueOUT implements EnviarEmailQueueOUTPort {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public EnviarEmailQueueOUT(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(String destinatario, String assunto, String texto){
        var hashmap = new HashMap<String, String>();
        var gson = new Gson();

        hashmap.put("destinatario", destinatario);
        hashmap.put("assunto", assunto);
        hashmap.put("texto", texto);

        rabbitTemplate.convertAndSend(RabbitMQConfig.AMQ_DIRECT_EXCHANGE, RabbitMQConfig.ENVIAR_EMAIL_ROUTING_KEY, gson.toJson(hashmap));
    }
}

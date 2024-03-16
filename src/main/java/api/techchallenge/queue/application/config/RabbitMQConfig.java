package api.techchallenge.queue.application.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AMQ_DIRECT_EXCHANGE = "amq.direct";
    public static final String PAGAMENTO_APROVADO_QUEUE = "queue_pagamento_aprovado";
    public static final String PAGAMENTO_APROVADO_ROUTING_KEY = "pagamento.aprovado";
    public static final String PAGAMENTO_ESTORNADO_QUEUE = "queue_pagamento_estornado";
    public static final String PAGAMENTO_ESTORNADO_ROUTING_KEY = "pagamento.estornado";
    public static final String ENVIAR_EMAIL_ROUTING_KEY = "enviar.email";

    @Bean
    public DirectExchange exchange(){
        return new DirectExchange(AMQ_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue pagamentoAprovadoQueue(){
        return new Queue(PAGAMENTO_APROVADO_QUEUE);
    }

    @Bean
    public Binding pagamentoAprovadoBinding(){
        return BindingBuilder.bind(pagamentoAprovadoQueue())
                .to(exchange())
                .with(PAGAMENTO_APROVADO_ROUTING_KEY);
    }

    @Bean
    public Queue pagamentoEstornadoQueue(){
        return new Queue(PAGAMENTO_ESTORNADO_QUEUE);
    }

    @Bean
    public Binding pagamentoEstornadoBinding(){
        return BindingBuilder.bind(pagamentoEstornadoQueue())
                .to(exchange())
                .with(PAGAMENTO_ESTORNADO_ROUTING_KEY);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}

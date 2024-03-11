package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.entities.Cliente;

import java.util.UUID;

public class ClienteBuilder {
    public static Cliente build(){
        return Cliente.builder()
                .id(UUID.randomUUID())
                .nome("Antonio")
                .email("antonio@email.com")
                .build();
    }
}

package api.techchallenge.queue.domain.entities;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Cliente {
    private UUID id;
    private String nome;
    private String email;
}

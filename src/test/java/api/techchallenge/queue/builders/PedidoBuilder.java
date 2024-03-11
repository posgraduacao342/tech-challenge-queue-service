package api.techchallenge.queue.builders;

import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PedidoBuilder {
    public static Pedido build(long posicaoFila){
        return Pedido.builder()
                .id(UUID.randomUUID())
                .clienteId(UUID.randomUUID())
                .dataRecebimento(LocalDateTime.now())
                .posicaoFila(posicaoFila)
                .status(StatusPedido.EM_PREPARACAO)
                .build();
    }

    public static Pedido build(){
        return build(1);
    }

    public static List<Pedido> buildList(){
        return Arrays.asList(
                build(1),
                build(2)
        );
    }
}

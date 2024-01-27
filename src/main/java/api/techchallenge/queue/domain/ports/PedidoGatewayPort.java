package api.techchallenge.queue.domain.ports;

import api.techchallenge.queue.domain.entities.Pedido;

import java.util.List;

public interface PedidoGatewayPort {
    List<Pedido> buscarPedidos();
}
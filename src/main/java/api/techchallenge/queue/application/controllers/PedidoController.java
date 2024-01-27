package api.techchallenge.queue.application.controllers;

import api.techchallenge.queue.domain.dtos.request.AtualizarStatusPedidoRequest;
import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import api.techchallenge.queue.domain.entities.Pedido;
import api.techchallenge.queue.domain.entities.UseCaseResponse;
import api.techchallenge.queue.domain.entities.UseCaseResponseNoData;
import api.techchallenge.queue.domain.enums.StatusPedido;
import api.techchallenge.queue.domain.usecases.AtualizarStatusPedidoUseCase;
import api.techchallenge.queue.domain.usecases.BuscarFilaDePedidosUseCase;
import api.techchallenge.queue.domain.usecases.BuscarPedidosUseCase;
import api.techchallenge.queue.domain.usecases.IncluirPedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {
    private final BuscarPedidosUseCase buscarPedidosUseCase;
    private final BuscarFilaDePedidosUseCase buscarFilaDePedidosUseCase;
    private final IncluirPedidoUseCase incluirPedidoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @GetMapping
    public ResponseEntity<UseCaseResponse<List<Pedido>>> buscarPedidos() {
        var response = buscarPedidosUseCase.execute();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping
    public ResponseEntity<UseCaseResponseNoData> incluirPedido(@RequestBody IncluirPedidoRequest request) {
        var response = incluirPedidoUseCase.execute(request);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("fila")
    public ResponseEntity<UseCaseResponse<List<Pedido>>> buscarFilaDePedidos() {
        var response = buscarFilaDePedidosUseCase.execute();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PatchMapping("{id}")
    public ResponseEntity<UseCaseResponseNoData> atualizarStatusPedido(@PathVariable(value="id") UUID idPedido, @RequestBody StatusPedido statusPedido){
        var response = atualizarStatusPedidoUseCase.execute(new AtualizarStatusPedidoRequest(idPedido, statusPedido));
        return new ResponseEntity<>(response, response.getStatus());
    }
}


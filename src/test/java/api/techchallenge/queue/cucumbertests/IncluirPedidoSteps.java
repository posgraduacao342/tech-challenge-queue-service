package api.techchallenge.queue.cucumbertests;

import api.techchallenge.queue.builders.IncluirPedidoRequestBuilder;
import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class IncluirPedidoSteps {
    private Response response;
    private IncluirPedidoRequest incluirPedidoRequest;

    // Cenário: Incluir um novo pedido

    @Dado("que tenho um novo pedido")
    public void que_tenho_um_novo_pedido() {
        incluirPedidoRequest = IncluirPedidoRequestBuilder.build();
    }

    @Quando("incluo o novo pedido")
    public void incluo_o_novo_pedido() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(incluirPedidoRequest)
                .when()
                .post(CucumberConstants.ENDPOINT_API_PEDIDO);
    }

    @Entao("deve retornar status ok")
    public void deve_retornar_status_ok() {
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    // Cenário: Incluir um novo pedido

//    @Dado("que tenho pedidos registrados")
//    public void tenho_pedidos_registrados(){
//
//    }
//    @Quando("eu busco todos os pedidos")
//    @Entao("deve retornar todos os pedidos")
}

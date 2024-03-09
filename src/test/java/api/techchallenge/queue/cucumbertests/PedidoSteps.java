package api.techchallenge.queue.cucumbertests;

import api.techchallenge.queue.builders.IncluirPedidoRequestBuilder;
import api.techchallenge.queue.domain.dtos.request.IncluirPedidoRequest;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PedidoSteps {
    private Response response;
    private IncluirPedidoRequest incluirPedidoRequest;

    // Cenário: Incluir um novo pedido

    @Dado("que tenho um novo pedido")
    public void que_tenho_um_novo_pedido() {
        incluirPedidoRequest = IncluirPedidoRequestBuilder.build(
                UUID.fromString("8c6775cc-eb37-4e4a-94dd-5919d82c3135")
                );
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

    // Cenário: Buscar todos os pedidos

    @Dado("que tenho pedidos registrados")
    public void tenho_pedidos_registrados(){
    }

    @Quando("eu busco todos os pedidos")
    public void busco_todos_os_pedidos(){
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(CucumberConstants.ENDPOINT_API_PEDIDO);
    }

    @Entao("deve retornar todos os pedidos")
    public void deve_retornar_todos_os_pedidos(){
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/PedidosSchema.json"));
    }

    // Cenário: Buscar todos os pedidos
    @Quando("eu busco a fila de pedidos")
    public void busco_fila_de_pedidos(){
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(CucumberConstants.ENDPOINT_API_PEDIDO + "/fila");
    }

    @Entao("deve retornar fila de pedidos")
    public void deve_retornar_fila_de_pedidos(){
        response.then()
                .body(matchesJsonSchemaInClasspath("./schemas/PedidosSchema.json"));
    }

}

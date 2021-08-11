package org.example;

import io.restassured.http.ContentType;
import org.example.Util.Base;
import org.example.Util.Login;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.hamcrest.*;

import static io.restassured.RestAssured.given;

public class App extends Base{
    private Login login = new Login();

    @Test
    public void validateNewRegister(){
        String requestBody ="{\n" +"\"acompanhante\": \"Camila\",\n" +
                "\"dataPartida\": \"2022-01-01\",\n" +
                "\"dataRetorno\": \"2022-01-02\",\n" +
                "\"localDeDestino\": \"Italia\",\n" +
                "\"regiao\": \"Sul\"\n"+
                "}";
        String name = given()
                .body(requestBody)
                .header("Authorization", login.admin.token)
                .contentType(ContentType.JSON)
        .when()
                .post("v1/viagens")
        .then()
                .statusCode(201)
                .log().body()
                .extract().path("data.acompanhante");
        Assert.assertThat(name, Matchers.is("Camila"));
    }

    @Test
    public void validateGetAllRegisters(){
        given()
                .header("Authorization", login.user.token)
                .contentType(ContentType.JSON)
        .when()
                .get("v1/viagens")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void validateEditExistingRegister(){
        String requestBody ="{\n" +"\"acompanhante\": \"Camila\",\n" +
                "\"dataPartida\": \"2022-01-01\",\n" +
                "\"dataRetorno\": \"2022-01-02\",\n" +
                "\"localDeDestino\": \"Italia\",\n" +
                "\"regiao\": \"Norte\"\n"+
                "}";
        given()
                .body(requestBody)
                .header("Authorization", login.admin.token)
                .contentType(ContentType.JSON)
        .when()
                .put("v1/viagens/1")
        .then()
                .statusCode(204)
                .log().body();
    }

    @Test
    public void validateDeleteExistingRegister(){
        given()
                .header("Authorization", login.admin.token)
                .contentType(ContentType.JSON)
        .when()
                .delete("v1/viagens/1")
        .then()
                .statusCode(204)
                .log().body();
    }
}

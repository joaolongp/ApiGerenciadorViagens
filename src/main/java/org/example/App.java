package org.example;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class App
{
    private static final String baseUri = "http://localhost:8089";
    private static final String basePath = "api";
    private static String tokenAdmin = null;
    private static String tokenUser = null;

    private String generateTokenAdmin(){
        String requestBody = "{" +
                "\"email\" : \"admin@email.com\"," +
                "\"senha\" : \"654321\"" +
                "}";
        tokenAdmin = given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .post("v1/auth")
        .then()
                .extract().path("data.token");

        return tokenAdmin;
    }

    private String generateTokenUser(){
        String requestBody = "{" +
                "\"email\" : \"usuario@email.com\"," +
                "\"senha\" : \"123456\"" +
                "}";
        tokenUser = given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .post("v1/auth")
        .then()
                .extract().path("data.token");

        return tokenUser;
    }

    @Test
    public void tc01ValidateGenerateAdminToken() {
        String requestBody = "{" +
                "\"email\" : \"admin@email.com\"," +
                "\"senha\" : \"654321\"" +
                "}";
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .post("v1/auth")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void tc02ValidateGenerateUserToken() {
        String requestBody = "{" +
                "\"email\" : \"usuario@email.com\"," +
                "\"senha\" : \"123456\"" +
                "}";
        given()
                .body(requestBody)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .post("v1/auth")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void tc03validateNewRegister(){
        String requestBody ="{\n" +"\"acompanhante\": \"Ingrid\",\n" +
                "\"dataPartida\": \"2022-01-01\",\n" +
                "\"dataRetorno\": \"2022-01-02\",\n" +
                "\"localDeDestino\": \"Escocia\",\n" +
                "\"regiao\": \"Sul\"\n"+
                "}";
        given()
                .body(requestBody)
                .header("Authorization", (tokenAdmin == null)?generateTokenAdmin():tokenAdmin)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .post("v1/viagens")
        .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void tc04validateGetAllRegisters(){
        given()
                .header("Authorization", (tokenUser == null)?generateTokenUser():tokenUser)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .get("v1/viagens")
        .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void tc05validateEditExistingRegister(){
        String requestBody ="{\n" +"\"acompanhante\": \"Camila\",\n" +
                "\"dataPartida\": \"2022-01-01\",\n" +
                "\"dataRetorno\": \"2022-01-02\",\n" +
                "\"localDeDestino\": \"Italia\",\n" +
                "\"regiao\": \"Norte\"\n"+
                "}";
        given()
                .body(requestBody)
                .header("Authorization", (tokenAdmin == null)?generateTokenAdmin():tokenAdmin)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .put("v1/viagens/1")
        .then()
                .statusCode(204)
                .log().body();
    }

    @Test
    public void tc06validateDeleteExistingRegister(){
        given()
                .header("Authorization", (tokenAdmin == null)?generateTokenAdmin():tokenAdmin)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .basePath(basePath)
        .when()
                .delete("v1/viagens/1")
        .then()
                .statusCode(204)
                .log().body();
    }
}

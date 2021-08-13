package org.example;

import io.restassured.http.ContentType;
import org.example.Data.Viagem;
import org.example.Util.Base;
import org.example.Util.Login;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class App extends Base{
    private Login login = new Login();

    @Test
    public void validateNewRegister(){
        Viagem viagem = new Viagem();
        String responseBody = given()
                .body(viagem.toJsonString())
                .header("Authorization", login.admin.token)
                .contentType(ContentType.JSON)
        .when()
                .post("v1/viagens")
        .then()
                .statusCode(201)
                .log().body()
                .extract().path("data");
        Assert.assertThat(responseBody, Matchers.notNullValue());
    }

    @Test
    public void validateGetAllRegisters(){
        String responseBody = given()
                .header("Authorization", login.user.token)
                .contentType(ContentType.JSON)
        .when()
                .get("v1/viagens")
        .then()
                .statusCode(200)
                .log().body()
                .extract().path("data");
        Assert.assertThat(responseBody, Matchers.notNullValue());
    }

    @Test
    public void validateGetRegisterByID(){
        String responseBody = given()
                .header("Authorization", login.user.token)
                .contentType(ContentType.JSON)
        .when()
                .get("v1/viagens")
        .then()
                .statusCode(200)
                .log().body()
                .extract().path("data");
        Assert.assertThat(responseBody, Matchers.notNullValue());
    }

    @Test
    public void validateEditExistingRegister(){
        given()
                .body(new Viagem().toJsonString())
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

package org.example.Util;

import io.restassured.http.ContentType;
import org.example.DTO.Admin;
import org.example.DTO.User;

import static io.restassured.RestAssured.given;

public class Login{
    public User user = new User();
    public Admin admin = new Admin();

    public Login(){
        admin.token = given()
                .body(admin.toJsonRequest())
                .contentType(ContentType.JSON)
        .when()
                .post("v1/auth")
        .then()
                .extract().path("data.token");

        user.token = given()
                .body(user.toJsonRequest())
                .contentType(ContentType.JSON)
        .when()
                .post("v1/auth")
        .then()
                .extract().path("data.token");
    }
}

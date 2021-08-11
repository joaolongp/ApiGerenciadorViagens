package org.example.Util;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class Base {
    public Base() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8089;
        RestAssured.basePath = "api";
    }
}

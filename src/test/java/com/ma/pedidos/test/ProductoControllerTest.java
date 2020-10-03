package com.ma.pedidos.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

@DisplayName("Producto Controller")
public class ProductoControllerTest {
	
	static final String URL_PRODUCTOS = "http://localhost:8888/mercantilandina/productos";
    
    @Test
    @Order(1)
    public void guardarProductoTest() {
    	
    	String json = " {\"id\": \"89efb206-2aa6-4e21-8a23-5765e3de1f31\",    \"nombre\": \"Jamón y morrones\",    "
        		+ "\"descripcionCorta\" : \"Pizza de jamón y morrones\",    \"descripcionLarga\" : "
        		+ "\"Mozzarella, jamón, morrones y aceitunas verdes\",    \"precioUnitario\" : 550.00    }";
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .post(URL_PRODUCTOS)
        .then()
            .statusCode(201);
    } 
	
    @Test
    @Order(3)
    public void modificarProductoTest() {
    	
    	String json = " {\"nombre\": \"Jamón y morrones\",    "
        		+ "\"descripcionCorta\" : \"Pizza de jamón y morrones\",    \"descripcionLarga\" : "
        		+ "\"Mozzarella, jamón, morrones y aceitunas verdes\",    \"precioUnitario\" : 550.00}";
    	
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .put(URL_PRODUCTOS+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then()
            .statusCode(204);
    }    
    
    @Test
    @Order(2)
    public void obtenerProductoExistenteTest() {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get(URL_PRODUCTOS+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then().assertThat()
            .statusCode(200)
            .body("id", equalTo("89efb206-2aa6-4e21-8a23-5765e3de1f31"))
    		.body("nombre", equalTo("Jamón y morrones"))
			.body("descripcionCorta", equalTo("Pizza de jamón y morrones"))
			.body("descripcionLarga", equalTo("Mozzarella, jamón, morrones y aceitunas verdes"))
			.body("precioUnitario", equalTo(550.0f));
        
    }
    
    @Test
    @Order(4)
    public void obtenerProductoInexistenteTest() {
        given()
            .contentType(ContentType.JSON)          
        .when()
            .get(URL_PRODUCTOS+"/1111b206-2aa6-4e21-8a23-5765e3de1f30")
        .then()
            .statusCode(404)
            .body("error", equalTo("Producto no encontrado"));
    }    
    
    @Test
    @Order(5)
    public void eliminarProductoTest() {
        given()
            .contentType(ContentType.JSON)          
        .when()
            .delete(URL_PRODUCTOS+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then()
            .statusCode(204);
    }  
    
}

package com.ma.pedidos.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class PedidoControllerTest {
    
	static final String URL = "http://localhost:8888/mercantilandina/pedidos";
	
    @Test
    @Order(8)
    public void crearPedidoTest() {

    	String fechaActual = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    	
    	String json = "{\"direccion\": \"Dorton Road 80\",\r\n"
				+ "\"email\": \"tsayb@opera.com\", \r\n"
				+ "\"telefono\": \"(0351) 48158101\",\r\n"
				+ "\"horario\": \"21:00\",\r\n"
				+ "\"detalle\": [\r\n"
				+ " { \"producto\": \"89efb206-2aa6-4e21-8a23-5765e3de1f39\", \r\n"
				+ "   \"cantidad\": 1 },\r\n"
				+ " { \"producto\": \"e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9\", \r\n"
				+ "   \"cantidad\": 1 }]}";
    	
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .post(URL)
        .then()
            .statusCode(201)
            .body("fecha", equalTo(fechaActual))
    		.body("direccion", equalTo("Dorton Road 80"))
			.body("email", equalTo("tsayb@opera.com"))
			.body("telefono", equalTo("(0351) 48158101"))
			.body("horario", equalTo("21:00"))
			.body("total", equalTo(1150.0f))
			.body("descuento", equalTo(false))
			.body("estado", equalTo("PENDIENTE"))
			
			.body("detalle[0].producto", equalTo("89efb206-2aa6-4e21-8a23-5765e3de1f39"))
			.body("detalle[0].nombre", equalTo("Jamón y morrones"))
			.body("detalle[0].cantidad", equalTo(1))
			.body("detalle[0].importe", equalTo(550.0f))
			
			.body("detalle[1].producto", equalTo("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9"))
			.body("detalle[1].nombre", equalTo("Palmitos"))
			.body("detalle[1].cantidad", equalTo(1))
			.body("detalle[1].importe", equalTo(600.0f));
    } 
	

    @Test
    @Order(9)
    public void crearPedidoConDescuentoTest() {

    	String fechaActual = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    	
    	String json = "{\"direccion\": \"Dorton Road 80\",\r\n"
				+ "\"email\": \"tsayb@opera.com\", \r\n"
				+ "\"telefono\": \"(0351) 48158101\",\r\n"
				+ "\"horario\": \"21:00\",\r\n"
				+ "\"detalle\": [\r\n"
				+ " { \"producto\": \"89efb206-2aa6-4e21-8a23-5765e3de1f39\", \r\n"
				+ "   \"cantidad\": 2 },\r\n"
				+ " { \"producto\": \"e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9\", \r\n"
				+ "   \"cantidad\": 2 }]}";
    	
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .post(URL)
        .then()
            .statusCode(201)
            .body("fecha", equalTo(fechaActual))
    		.body("direccion", equalTo("Dorton Road 80"))
			.body("email", equalTo("tsayb@opera.com"))
			.body("telefono", equalTo("(0351) 48158101"))
			.body("horario", equalTo("21:00"))
			.body("total", equalTo(1610.0f))
			.body("descuento", equalTo(true))
			.body("estado", equalTo("PENDIENTE"))
			
			.body("detalle[0].producto", equalTo("89efb206-2aa6-4e21-8a23-5765e3de1f39"))
			.body("detalle[0].nombre", equalTo("Jamón y morrones"))
			.body("detalle[0].cantidad", equalTo(2))
			.body("detalle[0].importe", equalTo(1100.0f))
			
			.body("detalle[1].producto", equalTo("e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9"))
			.body("detalle[1].nombre", equalTo("Palmitos"))
			.body("detalle[1].cantidad", equalTo(2))
			.body("detalle[1].importe", equalTo(1200.0f));
    } 
 
    @Test
    @Order(10)
    public void crearPedidoConErroresTest() {
    	
    	String json = "{    \"email\": \"tsayb@opera.com\",     \"telefono\": \"(0351) 48158101\",    "
    			+ "\"horario\": \"21:00\",    \"detalle\": [     { \"producto\": \"89efb206-2aa6-4e21-8a23-5765e3de1f39\" }, "
    			+ "{ \"producto\": \"e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9\",        \"cantidad\": 2 }]   }";    	
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .post(URL)
        .then()
            .statusCode(400)
            .body("errores[0].error", equalTo("la direccion no puede estar nula"))
            .body("errores[1].error", equalTo("falta ingresar cantidad"));
    } 
    
    
    
    @Test
    @Order(6)
    public void agregarProducto1PedidoTest() {

    	String json = "{\"id\": \"89efb206-2aa6-4e21-8a23-5765e3de1f39\",\"nombre\": \"Jamón y morrones\",\"descripcionCorta\""
    			+ " : \"Pizza de jamón y morrones\",\"descripcionLarga\" : \"Mozzarella, jamón, morrones y aceitunas verdes 8 porciones\","
    			+ "\"precioUnitario\" : 550.00}";

	        given()
	            .contentType(ContentType.JSON)
	            .body(json)
	            .post(ProductoControllerTest.URL)
	        .then()
	            .statusCode(201);

    }

    @Test
    @Order(7)
    public  void agregarProducto2PedidoTest() {
    	
    	String json = "{\"id\": \"e29ebd0c-39d2-4054-b0f4-ed2d0ea089a9\",\"nombre\": \"Palmitos\",\"descripcionCorta\" : "
    			+ "\"Pizza de Palmitos\",\"descripcionLarga\" : \"Mozzarella y Palmitos\",\"precioUnitario\" : 600.00 }";    	
    			
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .post(ProductoControllerTest.URL)
        .then()
            .statusCode(201);
		
    }
}

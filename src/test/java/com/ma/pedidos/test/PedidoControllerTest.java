package com.ma.pedidos.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class PedidoControllerTest {
    
	private static final String URL = "http://localhost:8888/mercantilandina/pedidos";
	
    @Test
    @Order(6)
    public void crearPedidoTest() {
    	String fechaActual = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
    	String jsonRequest = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			Map<String, Object> carMap = mapper.readValue(
					"{\"direccion\": \"Dorton Road 80\",\r\n"
					+ "\"email\": \"tsayb@opera.com\", \r\n"
					+ "\"telefono\": \"(0351) 48158101\",\r\n"
					+ "\"horario\": \"21:00\",\r\n"
					+ "\"detalle\": [\r\n"
					+ " { \"producto\": \"89efb206-2aa6-4e21-8a23-5765e3de1f31\", \r\n"
					+ "   \"cantidad\": 1 },\r\n"
					+ " { \"producto\": \"e29ebd0c-39d2-4054-b0f4-ed2d0ea089a1\", \r\n"
					+ "   \"cantidad\": 1 }]}",
			        Map.class		
					);
			jsonRequest = mapper.writeValueAsString(carMap);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
        given()
            .contentType(ContentType.JSON)
            .body(jsonRequest)
            .post(URL)
        .then()
            .statusCode(201)
            .body("fecha", equalTo(fechaActual))
    		.body("direccion", equalTo("Dorton Road 80"))
			.body("email", equalTo("tsayb@opera.com"))
			.body("telefono", equalTo("(0351) 48158101"))
			.body("horario", equalTo("21:00"))
			.body("total", equalTo(1150.00))
			.body("descuento", equalTo("true"))
			.body("estado", equalTo("(PENDIENTE"));
    } 
	
    
    /*
    @Test
    @Order(7)
    public void modificarProductoTest() {
    	String json = null;
    	ObjectMapper mapper = new ObjectMapper();
    	try {
			Map<String, Object> carMap = mapper.readValue(
					" {\"nombre\": \"Jamón y morrones\",    "
			        		+ "\"descripcionCorta\" : \"Pizza de jamón y morrones\",    \"descripcionLarga\" : "
			        		+ "\"Mozzarella, jamón, morrones y aceitunas verdes\",    \"precioUnitario\" : 550.00    }",
			        Map.class		
					);
			json = mapper.writeValueAsString(carMap);
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
        given()
            .contentType(ContentType.JSON)
            .body(json)
            .put(getURLApp()+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then()
            .statusCode(204);
    }    
    
    @Test
    @Order(8)
    public void obtenerProductoExistenteTest() {

        given()
            .contentType(ContentType.JSON)
        .when()
            .get(getURLApp()+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then()
            .statusCode(200)
            .body("id", equalTo("89efb206-2aa6-4e21-8a23-5765e3de1f31"))
    		.body("nombre", equalTo("Jamón y morrones"))
			.body("descripcionCorta", equalTo("Pizza de jamón y morrones"))
			.body("descripcionLarga", equalTo("Mozzarella, jamón, morrones y aceitunas verdes"));
        
    }
    
    @Test
    @Order(9)
    public void obtenerProductoInexistenteTest() {
        given()
            .contentType(ContentType.JSON)          
        .when()
            .get(getURLApp()+"/1111b206-2aa6-4e21-8a23-5765e3de1f30")
        .then()
            .statusCode(404)
            .body("error", equalTo("Producto no encontrado"));
    }    
    
    @Test
    @Order(10)
    public void eliminarProductoTest() {
        given()
            .contentType(ContentType.JSON)          
        .when()
            .delete(getURLApp()+"/89efb206-2aa6-4e21-8a23-5765e3de1f31")
        .then()
            .statusCode(204);
    }  
    */

}

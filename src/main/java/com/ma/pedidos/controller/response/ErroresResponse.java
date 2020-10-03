package com.ma.pedidos.controller.response;

import java.util.ArrayList;
import java.util.List;

public class ErroresResponse {
	private List<ErrorResponse> errores;

	public void addError(ErrorResponse errror) {
		if(this.errores == null)
			this.errores = new ArrayList<>();
		this.errores.add(errror);
	}	
	public ErroresResponse() { }
	
	public ErroresResponse(List<ErrorResponse> errores) {
		super();
		this.errores = errores;
	}

	public List<ErrorResponse> getErrores() {
		return errores;
	}

	public void setErrores(List<ErrorResponse> errores) {
		this.errores = errores;
	}
	
	
}

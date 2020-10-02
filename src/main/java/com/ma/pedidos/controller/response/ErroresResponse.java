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
	
	public ErroresResponse(List<ErrorResponse> errrores) {
		super();
		this.errores = errrores;
	}

	public List<ErrorResponse> getErrrores() {
		return errores;
	}

	public void setErrrores(List<ErrorResponse> errrores) {
		this.errores = errrores;
	}
	
	
}

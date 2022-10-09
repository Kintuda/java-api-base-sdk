package com.api.sdk.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CidadeInfoDTO implements Serializable {

	@SerializedName("area_km2")
	private String areaKm2;

	@SerializedName("codigo_ibge")
	private String codigoIbge;

	public void setAreaKm2(String areaKm2){
		this.areaKm2 = areaKm2;
	}

	public String getAreaKm2(){
		return areaKm2;
	}

	public void setCodigoIbge(String codigoIbge){
		this.codigoIbge = codigoIbge;
	}

	public String getCodigoIbge(){
		return codigoIbge;
	}

	@Override
 	public String toString(){
		return
			"CidadeInfoDTO{" +
			"area_km2 = '" + areaKm2 + '\'' +
			",codigo_ibge = '" + codigoIbge + '\'' +
			"}";
		}
}

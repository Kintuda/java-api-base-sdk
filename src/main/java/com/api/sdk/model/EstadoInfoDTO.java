package com.api.sdk.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class EstadoInfoDTO implements Serializable {

	@SerializedName("area_km2")
	private String areaKm2;

	@SerializedName("codigo_ibge")
	private String codigoIbge;

	@SerializedName("nome")
	private String nome;

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

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return nome;
	}

	@Override
 	public String toString(){
		return
			"EstadoInfoDTO{" +
			"area_km2 = '" + areaKm2 + '\'' +
			",codigo_ibge = '" + codigoIbge + '\'' +
			",nome = '" + nome + '\'' +
			"}";
		}
}

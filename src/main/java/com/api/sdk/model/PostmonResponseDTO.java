package com.api.sdk.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PostmonResponseDTO implements Serializable {

	@SerializedName("bairro")
	private String bairro;

	@SerializedName("cidade")
	private String cidade;

	@SerializedName("logradouro")
	private String logradouro;

	@SerializedName("estado_info")
	private EstadoInfoDTO estadoInfo;

	@SerializedName("cep")
	private String cep;

	@SerializedName("cidade_info")
	private CidadeInfoDTO cidadeInfo;

	@SerializedName("estado")
	private String estado;

	public void setBairro(String bairro){
		this.bairro = bairro;
	}

	public String getBairro(){
		return bairro;
	}

	public void setCidade(String cidade){
		this.cidade = cidade;
	}

	public String getCidade(){
		return cidade;
	}

	public void setLogradouro(String logradouro){
		this.logradouro = logradouro;
	}

	public String getLogradouro(){
		return logradouro;
	}

	public void setEstadoInfo(EstadoInfoDTO estadoInfo){
		this.estadoInfo = estadoInfo;
	}

	public EstadoInfoDTO getEstadoInfo(){
		return estadoInfo;
	}

	public void setCep(String cep){
		this.cep = cep;
	}

	public String getCep(){
		return cep;
	}

	public void setCidadeInfo(CidadeInfoDTO cidadeInfo){
		this.cidadeInfo = cidadeInfo;
	}

	public CidadeInfoDTO getCidadeInfo(){
		return cidadeInfo;
	}

	public void setEstado(String estado){
		this.estado = estado;
	}

	public String getEstado(){
		return estado;
	}

	@Override
 	public String toString(){
		return
			"PostmonResponseDTO{" +
			"bairro = '" + bairro + '\'' +
			",cidade = '" + cidade + '\'' +
			",logradouro = '" + logradouro + '\'' +
			",estado_info = '" + estadoInfo + '\'' +
			",cep = '" + cep + '\'' +
			",cidade_info = '" + cidadeInfo + '\'' +
			",estado = '" + estado + '\'' +
			"}";
		}
}

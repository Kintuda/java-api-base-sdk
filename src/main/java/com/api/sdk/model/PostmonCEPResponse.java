package com.api.sdk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostmonCEPResponse {

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("estado")
    private String estado;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("estado_info")
    private EstadoInfo estadoInfo;

    @JsonProperty("cidade_info")
    private CidadeInfo cidadeInfo;

    @JsonProperty("cep")
    private String cep;

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public EstadoInfo getEstadoInfo() {
        return estadoInfo;
    }

    public CidadeInfo getCidadeInfo() {
        return cidadeInfo;
    }

    public String getCep() {
        return cep;
    }

    public static class EstadoInfo {

        @JsonProperty("codigo_ibge")
        private String codigoIbge;

        @JsonProperty("nome")
        private String nome;

        @JsonProperty("area_km2")
        private String areaKm2;

        public String getCodigoIbge() {
            return codigoIbge;
        }

        public String getNome() {
            return nome;
        }

        public String getAreaKm2() {
            return areaKm2;
        }
    }

    public static class CidadeInfo {
        @JsonProperty("codigo_ibge")
        private String codigoIbge;

        @JsonProperty("area_km2")
        private String areaKm2;

        public String getCodigoIbge() {
            return codigoIbge;
        }

        public String getAreaKm2() {
            return areaKm2;
        }
    }

}

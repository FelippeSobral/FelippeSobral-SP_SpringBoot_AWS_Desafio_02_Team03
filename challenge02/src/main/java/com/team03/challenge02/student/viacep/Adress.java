package com.team03.challenge02.student.viacep;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Adress (
    @JsonProperty("cep") String zipCode,
    @JsonProperty("logradouro") String street,
    @JsonProperty("bairro")String neighborhood,
    @JsonProperty("localidade")String city,
    @JsonProperty("uf") String state
){}


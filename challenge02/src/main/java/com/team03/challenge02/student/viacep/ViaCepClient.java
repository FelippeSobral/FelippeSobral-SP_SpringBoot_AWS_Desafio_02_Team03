package com.team03.challenge02.student.viacep;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/",name = "viaCepClient")
public interface ViaCepClient {
    @GetMapping("/{zipCode}/json/")
    Adress getAdress(@PathVariable("zipCode") String zipCode);
}

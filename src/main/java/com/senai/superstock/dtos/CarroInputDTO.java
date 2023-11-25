package com.senai.superstock.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor
public class CarroInputDTO {
    
    private Long id;
    @NotNull
    private String chassi;
    private String modelo;
    private String cor;
    private String combustivel;
    private String observacoes;
    private String fotos;
    @NotNull
    private String placa;
    private boolean reservado;
    
}

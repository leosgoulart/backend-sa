package com.senai.superstock.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class CarroOutPutDTO {
    
    private Long id;
    private String chassi;
    private String modelo;
    private String cor;
    private String combustivel;
    private String observacoes;
    private String fotos;
    private String placa;
    private boolean reservado;
}

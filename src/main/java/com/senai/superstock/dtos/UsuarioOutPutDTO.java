package com.senai.superstock.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data @AllArgsConstructor @NoArgsConstructor
public class UsuarioOutPutDTO {
    
       private Long id;
    private String nome;
    private Integer cpf;
    private String email;
    private String senha;
}

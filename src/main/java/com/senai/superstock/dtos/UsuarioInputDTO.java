package com.senai.superstock.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioInputDTO {

    private Long id;
    private String nome;
    private String cpf;
    @NotNull
    private String email;
    @NotNull
    private String senha;

}

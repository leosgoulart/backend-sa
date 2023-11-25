package com.senai.superstock;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.senai.superstock.entities.Usuario;
import com.senai.superstock.repositories.UsuarioRepository;
import com.senai.superstock.services.UsuarioService;

@SpringBootTest
public class UsuarioServiceTests {

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    public void testCreate() {
        // Criação de um objeto de teste
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");
        usuario.setCpf("12345678901");
        usuario.setSenha("senhaTeste");

        // Configuração do comportamento do mock
        when(usuarioRepositoryMock.save(any())).thenReturn(new Usuario());

        // Execução do método de teste
        Usuario usuarioCriado = usuarioService.create(usuario);

        // Verificações
        assertNotNull(usuarioCriado);
        // Adicione mais verificações conforme necessário
    }

    @Test
    public void testUpdate() {
        // Criação de um objeto de teste
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("TesteAtualizado");

        // Configuração do comportamento do mock
        when(usuarioRepositoryMock.existsById(1L)).thenReturn(true);
        when(usuarioRepositoryMock.save(any())).thenReturn(new Usuario());

        // Execução do método de teste
        Usuario usuarioAtualizado = usuarioService.update(usuario);

        // Verificações
        assertNotNull(usuarioAtualizado);
        // Adicione mais verificações conforme necessário
    }

    @Test
    public void testRead() {
        // Configuração do comportamento do mock
        when(usuarioRepositoryMock.findById(1L)).thenReturn(java.util.Optional.of(new Usuario()));

        // Execução do método de teste
        Usuario usuarioLido = usuarioService.read(1L);

        // Verificações
        assertNotNull(usuarioLido);
        // Adicione mais verificações conforme necessário
    }

    @Test
    public void testLoadUserByUsername() {
        // Criação de um objeto de teste
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678901");
        usuario.setSenha("senhaTeste");

        // Configuração do comportamento do mock
        when(usuarioRepositoryMock.findByCpf("12345678901")).thenReturn(usuario);

        // Execução do método de teste
        UserDetails userDetails = usuarioService.loadUserByUsername("12345678901");

        // Verificações
        assertNotNull(userDetails);
        // Adicione mais verificações conforme necessário
    }
}

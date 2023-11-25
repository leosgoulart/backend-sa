package com.senai.superstock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.senai.superstock.dtos.CarroInputDTO;
import com.senai.superstock.dtos.CarroOutPutDTO;
import com.senai.superstock.entities.Carro;
import com.senai.superstock.entities.Usuario;
import com.senai.superstock.repositories.CarroRepository;
import com.senai.superstock.services.CarroService;
import com.senai.superstock.services.FilesStorageService;

@SpringBootTest
public class CarroServiceTests {

    @Mock
    private CarroRepository carroRepositoryMock;

    @Mock
    private FilesStorageService storageServiceMock;

    @InjectMocks
    private CarroService carroService;

    @Test
    public void testCreate() {
        // Criação de um objeto de teste
        CarroInputDTO carroInputDTO = new CarroInputDTO();
        carroInputDTO.setChassi("123456");
        carroInputDTO.setPlaca("PlacaTeste");
        // Adicione outros campos conforme necessário

        // Configuração do comportamento dos mocks
        when(carroRepositoryMock.save(any())).thenReturn(new Carro());
        when(storageServiceMock.save(any())).thenReturn("nomeArquivo");

        // Execução do método de teste
        CarroOutPutDTO carroCriado = carroService.create(carroInputDTO, new Usuario());

        // Verificações
        assertNotNull(carroCriado);
        // Adicione mais verificações conforme necessário
    }

    @Test
    public void testUpdate() {
        // Criação de um objeto de teste
        CarroInputDTO carroInputDTO = new CarroInputDTO();
        carroInputDTO.setId(1L);
        carroInputDTO.setChassi("123456");
        carroInputDTO.setPlaca("ModeloTesteAtualizado");
        // Adicione outros campos conforme necessário

        // Configuração do comportamento dos mocks
        when(carroRepositoryMock.existsById(1L)).thenReturn(true);
        when(carroRepositoryMock.save(any())).thenReturn(new Carro());

        // Execução do método de teste
        CarroOutPutDTO carroAtualizado = carroService.update(carroInputDTO);

        // Verificações
        assertNotNull(carroAtualizado);
        // Adicione mais verificações conforme necessário
    }
}

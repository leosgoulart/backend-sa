package com.senai.superstock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.senai.superstock.dtos.CarroInputDTO;
import com.senai.superstock.dtos.CarroOutPutDTO;
import com.senai.superstock.entities.Carro;
import com.senai.superstock.entities.FileInfo;
import com.senai.superstock.entities.Usuario;
import com.senai.superstock.repositories.CarroRepository;

@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private FilesStorageService storageService;

//     @Transactional
//     public CarroOutPutDTO create(CarroInputDTO carroInputDTO, Usuario usuario) {
//     Carro carroTemporario = converterDtoParaEntidade(carroInputDTO);
//     // carroTemporario.setCreatedBy(usuario);
//     // Carro carroCriado = repository.save(carroTemporario);
//     return converterEntidadeParaDTO(carroCriado);
// }




    public CarroOutPutDTO converterEntidadeParaDTO(Carro carro) {
        CarroOutPutDTO dtoSaida = new CarroOutPutDTO();
        dtoSaida.setId(carro.getId());
        dtoSaida.setChassi(carro.getChassi());
        dtoSaida.setModelo(carro.getModelo());
        dtoSaida.setCor(carro.getCor());
        dtoSaida.setCombustivel(carro.getCombustivel());
        dtoSaida.setObservacoes(carro.getObservacoes());
        dtoSaida.setFotos(carro.getFotos());
        dtoSaida.setPlaca(carro.getPlaca());
        dtoSaida.setReservado(carro.isReservado());
        return dtoSaida;
    }

    public Carro converterDtoParaEntidade(CarroInputDTO dto) {
        Carro carro = new Carro();
        carro.setChassi(dto.getChassi());
        carro.setModelo(dto.getModelo());
        carro.setCor(dto.getCor());
        carro.setCombustivel(dto.getCombustivel());
        carro.setObservacoes(dto.getObservacoes());
        carro.setFotos(dto.getFotos());
        carro.setPlaca(dto.getPlaca());
        carro.setReservado(dto.isReservado());
        carro.setId(dto.getId());
        return carro;
    }

    @Transactional
    public CarroOutPutDTO create(CarroInputDTO carroInputDTO, Usuario usuario) {
        Carro carroCriado = new Carro();
        carroCriado.setChassi(carroInputDTO.getChassi());
        carroCriado.setModelo(carroInputDTO.getModelo());
        carroCriado.setCor(carroInputDTO.getCor());
        carroCriado.setCombustivel(carroInputDTO.getCombustivel());
        carroCriado.setObservacoes(carroInputDTO.getObservacoes());
        carroCriado.setFotos(carroInputDTO.getFotos());
        carroCriado.setPlaca(carroInputDTO.getPlaca());
        carroCriado.setReservado(carroInputDTO.isReservado());
    
        Carro carroSalvo = repository.save(carroCriado);
    
        return converterEntidadeParaDTO(carroSalvo);
    }
    

    public CarroOutPutDTO read(Long id) {
        Carro carro = repository.findById(id).orElse(null);
        return (carro != null) ? converterEntidadeParaDTO(carro) : null;
    }

    public List<CarroOutPutDTO> list(Pageable page) {
        return repository.findAll(page).map(this::converterEntidadeParaDTO).toList();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public CarroOutPutDTO update(CarroInputDTO carroInputDTO) {
        if (carroInputDTO.getId() == null) {
            carroInputDTO.setId(99L);
        }

        if (repository.existsById(carroInputDTO.getId())) {
            Carro carroAtualizado = repository.save(converterDtoParaEntidade(carroInputDTO));
            return converterEntidadeParaDTO(carroAtualizado);
        } else {
            return null;
        }
    }

    public void uploadPhoto(Long id, MultipartFile file) {
        if (repository.existsById(id)) {
            var filename = storageService.save(file);
            var fileInfo = new FileInfo(filename);
            var carro = repository.findById(id).orElse(null);

            if (carro != null) {
                carro.setFotos(fileInfo.getFilename());
                repository.save(carro);
            }
        }
    }

    public Resource getFoto(Long id) {
        Carro carro = repository.findById(id).orElse(null);

        if (carro != null && carro.getFotos() != null) {
            return storageService.load(carro.getFotos());
        }

        return null;
    }
}

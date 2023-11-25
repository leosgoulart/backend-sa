package com.senai.superstock.controllers;

import com.senai.superstock.entities.Carro;
import com.senai.superstock.services.CarroService;
import com.senai.superstock.dtos.CarroInputDTO;
import com.senai.superstock.dtos.CarroOutPutDTO;

import com.senai.superstock.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/carros")
@CrossOrigin("*")
public class CarroController {

    @Autowired
    private CarroService service;

    @PostMapping
    public ResponseEntity<CarroOutPutDTO> create(@RequestBody @Valid CarroInputDTO carro) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (Usuario) auth.getPrincipal();
        
        CarroOutPutDTO carroCriado = service.create(carro, usuario);
        return new ResponseEntity<>(carroCriado, HttpStatus.CREATED);
    }
    

    @PutMapping
    public ResponseEntity<CarroOutPutDTO> put(@RequestBody CarroInputDTO carro) {
        CarroOutPutDTO carroAtualizado = service.update(carro);
        return ResponseEntity.ok(carroAtualizado);
    }

    @PostMapping("/{id}/photo")
    @Transactional
    public ResponseEntity<?> uploadFile(@PathVariable Long id, @RequestParam MultipartFile file) {
        String message = "";
        try {
            service.uploadPhoto(id, file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " +
                    file.getOriginalFilename() +
                    ". Error: " +
                    e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
      Resource file = service.getFoto(id);
      
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CarroOutPutDTO>> getList(Pageable page) {
        List<CarroOutPutDTO> lista = (List<CarroOutPutDTO>) service.list(page);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarroOutPutDTO> getRead(@PathVariable Long id) {
        CarroOutPutDTO carroEncontrado = service.read(id);
        return ResponseEntity.ok(carroEncontrado);
    }
}

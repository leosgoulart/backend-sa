// package com.senai.superstock.entities;

// import java.time.LocalDateTime;

// import com.example.superstock.dtos.ReservaInputDTO;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;


// @Entity
// @Data @AllArgsConstructor @NoArgsConstructor
// public class Reserva {
    

//     public Reserva(ReservaInputDTO dto) {
//     }
    
//     @Id
//     @GeneratedValue(ReservaInputDTO dtostrategy = GenerationType.IDENTITY)
//     private Long id;
//     @ManyToOne
//     private Carro carro;
//     @ManyToOne
//     private Vendedor vendedor;
//     @ManyToOne
//     private Gerente gerente;
//     private LocalDateTime dataReserva;
//     private Boolean reservaConfirmada;


// }

package com.moto.service.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Moto {

 @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private Long usuarioId;

    
}

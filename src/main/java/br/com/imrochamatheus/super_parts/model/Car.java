package br.com.imrochamatheus.super_parts.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Carros")
public class Car {

    @Column(name = "CarroID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NomeModelo")
    private String model;

    @Column(name = "Fabricante")
    private String producer;

    @Column(name = "CodigoUnico")
    private String code;
}

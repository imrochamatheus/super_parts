package br.com.imrochamatheus.super_parts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Pecas")
public class Part {

    @Id
    @Column(name = "PecaID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Nome")
    private String name;

    @Column(name = "Descricao")
    private String description;

    @Column(name = "NumeroSerie")
    private String serial;

    @Column(name = "Fabricante")
    private String producer;

    @Column(name = "ModeloCarro")
    private String carModel;

    @Column(name = "CarroID")
    private int carId;
}

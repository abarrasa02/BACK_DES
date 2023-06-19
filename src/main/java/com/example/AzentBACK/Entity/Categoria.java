package com.example.AzentBACK.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "azento_categorias")
@AllArgsConstructor
@NoArgsConstructor
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "activo")
    private String activo;

    @Column(name = "imagen", nullable = true, length = 100000)
    private byte[] imagen;
}

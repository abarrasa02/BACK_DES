package com.example.AzentBACK.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "azento_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDUsuario", referencedColumnName = "idUsuario", foreignKey = @ForeignKey(name = "FK_Pedidos_Usuarios"))
    private Usuario usuario;

    @Column(name = "Total", precision = 10, scale = 2, nullable = false)
    private Float total;

    @Column(name = "FechaPedido", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaPedido;
}

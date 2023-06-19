package com.example.AzentBACK.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {

    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private int cantidad;
}

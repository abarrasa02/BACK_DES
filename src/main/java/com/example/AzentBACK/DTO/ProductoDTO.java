package com.example.AzentBACK.DTO;

import com.example.AzentBACK.Entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Float precio;
    private String activo;
    private byte[] imagen;
    private Long  categoria;
}

package com.example.AzentBACK.Provider;

import com.example.AzentBACK.DTO.CarritoDTO;
import com.example.AzentBACK.Entity.Carrito;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CarritoProvider {



    public MessageResponseDto<String> addProductoCarrito(Carrito carrito);
    public MessageResponseDto<String>deleteCarrito(Long id);

    public MessageResponseDto<List<Carrito>> saveModifiedItems(List<Carrito> carritoList);
    public MessageResponseDto<List<Carrito>>getByUsuario(Long id);
}

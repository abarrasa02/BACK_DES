package com.example.AzentBACK.Controller;

import com.example.AzentBACK.DTO.CarritoDTO;
import com.example.AzentBACK.Entity.Carrito;
import com.example.AzentBACK.Provider.CarritoProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    CarritoProvider carritoProvider;


    @PostMapping("/addCarrito")
    public MessageResponseDto<String> addProductoCarrito(@RequestBody Carrito carrito){
        try {
            return  carritoProvider.addProductoCarrito(carrito);
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al añadir el produto al carrito");
        }

    }

    @DeleteMapping("/delete/{id}")
    public MessageResponseDto<String>deleteCarrito(@PathVariable Long id){
        try {
            return  carritoProvider.deleteCarrito(id);
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al eliminar el produto");
        }
    }

    @PutMapping("/modificar")
    public MessageResponseDto<List<Carrito>> saveModifiedItems(@RequestBody List<Carrito> carritoList){
        try {
            return  carritoProvider.saveModifiedItems(carritoList);
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al modificar los prodcutos");
        }
    }

    @GetMapping("/productoUser/{id}")
    public MessageResponseDto<List<Carrito>>getByUsuario(@PathVariable Long id){
        try {
            return carritoProvider.getByUsuario(id);
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger los productos del carrito");
        }
    }

}

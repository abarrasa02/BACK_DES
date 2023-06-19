package com.example.AzentBACK.Controller;

import com.example.AzentBACK.Entity.Pedido;
import com.example.AzentBACK.Provider.PedidoProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoProvider pedidoProvider;
    @PostMapping("/add")
    public MessageResponseDto<Pedido>crearPedido(@RequestBody Pedido pedido){
        try {
          return   pedidoProvider.crearPedido(pedido);
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al crear el pedido");
        }
    }

    @GetMapping("/getAll")
    public MessageResponseDto<List<Pedido>>getAll(){
        try {
            return pedidoProvider.getAll();
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger los pedidos");
        }
    }
    @GetMapping("/getByIdUser/{id}")
    public MessageResponseDto<List<Pedido>>getByIdUser(@PathVariable("id") Long id){
        try {
            return pedidoProvider.getByIdUser(id);
        }catch (Exception e){
            return  MessageResponseDto.fail("No se han podido recoger pedidos");
        }
    }
}

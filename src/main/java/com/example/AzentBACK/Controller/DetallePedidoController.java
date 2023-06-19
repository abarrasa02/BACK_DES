package com.example.AzentBACK.Controller;

import com.example.AzentBACK.Entity.DetallePedido;
import com.example.AzentBACK.Provider.DetallePedidoProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detallePedido")
public class DetallePedidoController {

    @Autowired
    DetallePedidoProvider detallePedidoProvider;
    @PostMapping("/add")
    MessageResponseDto<Long>crearDetallePedido( @RequestBody DetallePedido detallePedido){
        try {
            return detallePedidoProvider.crearDetallePedido(detallePedido);
        }catch (Exception e){
            return MessageResponseDto.fail("Error al crear el detalle pedido");
        }
    }
    @GetMapping("/getAll")
    MessageResponseDto<List<DetallePedido>>getAll(){
        try {
            return  detallePedidoProvider.getAll();
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger los detalle pedido");
        }
    }
}

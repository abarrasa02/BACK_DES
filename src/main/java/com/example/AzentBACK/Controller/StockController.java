package com.example.AzentBACK.Controller;


import com.example.AzentBACK.Entity.Stock;
import com.example.AzentBACK.Provider.StockProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockProvider stockProvider;



    @PostMapping("/add/{id}")
    public MessageResponseDto<String> añadirStock(@PathVariable("id") Long idProducto){
        try {
            return stockProvider.añadirStock(idProducto);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido añadir al stock");
        }
    }


    @PatchMapping("/edit/{id}")
     public MessageResponseDto<String>modificarStock(@PathVariable("id") Long idStock,@RequestBody Stock stock){
        try {
            return stockProvider.modificarStock(idStock,stock);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido modificar el stock");
        }
    }


    @GetMapping("/comprobar")
    MessageResponseDto<Boolean>comprobarStock(@RequestParam("idProducto") Long idProducto,@RequestParam("cantidad") int Cantidad){
        try {
            return stockProvider.comprobarStock(idProducto,Cantidad);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido comprobar el stock");
        }
    }


    @PatchMapping("/restarStock")
    public MessageResponseDto<String> restarStock(@RequestParam("idProducto") Long idProducto,@RequestParam("cantidad") int cantidad){
        try {
            return stockProvider.restarStock(idProducto,cantidad);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido restar el stock");
        }
    }

}

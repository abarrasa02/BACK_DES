package com.example.AzentBACK.Controller;

import com.example.AzentBACK.DTO.ProductoDTO;
import com.example.AzentBACK.Entity.Producto;
import com.example.AzentBACK.Provider.ProductoProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoProvider productoProvider;

    @PostMapping("/add")
    public MessageResponseDto<Long>addProducto(@RequestBody ProductoDTO productoDto){
        try {
            return productoProvider.addProducto(productoDto);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido añadir el producto correctamente");
        }
    }




    @DeleteMapping("/delete/{id}")
    public MessageResponseDto<String>deleteProducto(@PathVariable Long id){
        try {
            return  productoProvider.deleteProducto(id);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido eliminar el producto");
        }
    }
    @PatchMapping("/edit/{id}")
    public  MessageResponseDto<Long>updateProducto(@PathVariable("id")Long id,@RequestBody ProductoDTO productoDto){
        try {
            return productoProvider.updateProducto(id,productoDto);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido modificar el producto correctamente");
        }
    }

    @GetMapping("/all")
    public MessageResponseDto<List<Producto>>getProductos(){
        try {
            return productoProvider.getProductos();
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger los productos");
        }
    }
    @GetMapping("/producto/{id}")
    public MessageResponseDto<Producto>findById(@PathVariable("id")Long id){
        try {
            return productoProvider.findById(id);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado el producto");
        }
    }

    @PostMapping("/getImage")
    public  MessageResponseDto<byte[]>getImage(@RequestParam("image") MultipartFile image, @RequestParam ("id")Long id){
        try {
            return productoProvider.getImage(id,image);
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger las imagen");
        }
    }

    @GetMapping("/filter/{id}")
    public MessageResponseDto<List<Producto>>filterByIdCategoria(@PathVariable("id")Long id){
        try {
            return  productoProvider.filterByIdCategoria(id);
        }catch (Exception e){
            return MessageResponseDto.fail("No se han podido filtrar los productos por categorias");
        }
    }



}
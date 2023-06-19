package com.example.AzentBACK.Controller;

import com.example.AzentBACK.DTO.CategoriaDTO;
import com.example.AzentBACK.Entity.Categoria;
import com.example.AzentBACK.Provider.CategoriaProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;



@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaProvider categoriaProvider;
    @GetMapping("/all")
    public MessageResponseDto<List<Categoria>>getAllCategorias(){
        try {
            return categoriaProvider.getAllCategorias();
        }catch (Exception e){
            return MessageResponseDto.fail("No se han encontrado categorias");
        }
    }

    @PostMapping("/add")
    public MessageResponseDto<Long>addCategoria(@RequestBody CategoriaDTO categoriaDTO){
        try {
            return categoriaProvider.addCategoria(categoriaDTO);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido añadir una categoria");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public MessageResponseDto<String>deleteCategoria(@PathVariable("id")Long id){
        try {
            return   categoriaProvider.deleteCategoria(id);
        }catch (Exception e){
            return  MessageResponseDto.fail("No se ha podido añadir la categoria");
        }
    }
    @PatchMapping("/edit/{id}")
    public MessageResponseDto<Long>updateCategoria(@PathVariable("id")Long id,@RequestBody CategoriaDTO categoriaDTO){
        try {
            return categoriaProvider.updateCategoria(id,categoriaDTO);

        }catch (Exception e){
            return  MessageResponseDto.fail("No se ha podido modificar correctamente la categoria");
        }
    }
    @GetMapping("/categoria/{id}")
    public MessageResponseDto<Categoria>findById(@PathVariable("id")Long id){
        try {
            return categoriaProvider.findById(id);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado la categoria");
        }
    }
    @PostMapping("/getImage")
    public  MessageResponseDto<byte[]>getImage(@RequestParam("image") MultipartFile image,@RequestParam ("id")Long id){
        try {
            return categoriaProvider.getImage(id,image);
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger las imagen");
        }
    }


}

package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.DTO.CategoriaDTO;
import com.example.AzentBACK.DTO.ProductoDTO;
import com.example.AzentBACK.Entity.Categoria;
import com.example.AzentBACK.Provider.CategoriaProvider;
import com.example.AzentBACK.Repository.CategoriaRepository;
import com.example.AzentBACK.Utils.ImagenUtil;
import com.example.AzentBACK.Utils.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoriaProviderImpl  implements CategoriaProvider {

    @Autowired
    CategoriaRepository categoriaRepository;


    private ModelMapper modelMapper=new ModelMapper();
    public MessageResponseDto<List<Categoria>> getAllCategorias(){
        try {
            List<Categoria>listCategorias=categoriaRepository.selectCatActivas();
            listCategorias.stream().forEach(categoria -> categoria.setImagen(ImagenUtil.decompressImage(categoria.getImagen())));
            if(listCategorias.isEmpty()){
                return MessageResponseDto.fail("No se ha encontrado categorias");
            }else {
                return MessageResponseDto.success(listCategorias);
            }
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado categorias");
        }
    }
    public MessageResponseDto<Long>addCategoria(CategoriaDTO cat){
        try {
            Categoria categoria=new Categoria();
            categoria=modelMapper.map(cat,Categoria.class);
            categoria=categoriaRepository.save(categoria);
            return MessageResponseDto.success(categoria.getId());
        }catch (Exception e){

            return  MessageResponseDto.fail("No se ha podido añadir una nueva categorias");
        }
    }
    public MessageResponseDto<String>deleteCategoria(Long id){
        try {
            Optional<Categoria> categoria=categoriaRepository.findById(id);
            if (categoria.isPresent()){
                categoria.get().setActivo("N");
                categoriaRepository.save(categoria.get());
                return MessageResponseDto.success("Se ha eliminado la categoria correctamente");
            }else{
                return  MessageResponseDto.success("No se ha podido eliminar la categoria");
            }
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido encontrar la categoria");
        }
    }
    public MessageResponseDto<Long>updateCategoria(Long id, CategoriaDTO categoriaDTO){
        try {
            Categoria categoria=modelMapper.map(categoriaDTO,Categoria.class);
            Optional<Categoria> categoria1=categoriaRepository.findById(id);
            if (!categoria1.isPresent()){
                return MessageResponseDto.fail("No se ha encontrado esa categoria");
            }
            categoria.setId(id);
            categoria=categoriaRepository.save(categoria);
            return MessageResponseDto.success(categoria.getId());
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido modificar la categoria correctamente");
        }
    }
    public MessageResponseDto<Categoria>findById(Long id){
        try {
            Optional<Categoria>categoria=categoriaRepository.findById(id);

            if (!categoria.isPresent()){
                return MessageResponseDto.fail("No se ha encontrado la categoria");
            }
            Categoria cat=categoria.get();
            cat.setImagen((ImagenUtil.decompressImage(cat.getImagen())));
            return  MessageResponseDto.success(cat);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado la categoria");
        }
    }

    public MessageResponseDto<byte[]>getImage(Long categoriaId,MultipartFile imagen){
        try {
            Optional<Categoria>cat=categoriaRepository.findById(categoriaId);
            cat.get().setImagen(ImagenUtil.compressImage(imagen.getBytes()));
            categoriaRepository.save(cat.get());
           return MessageResponseDto.success(imagen.getBytes());
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al recoger la iamgen");
        }
    }

}
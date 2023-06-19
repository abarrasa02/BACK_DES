package com.example.AzentBACK.Provider;

import com.example.AzentBACK.DTO.CategoriaDTO;
import com.example.AzentBACK.Entity.Categoria;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoriaProvider {

    MessageResponseDto<List<Categoria>>getAllCategorias();
    MessageResponseDto<Long> addCategoria(CategoriaDTO cat);

    MessageResponseDto<String>deleteCategoria(Long id);
    MessageResponseDto<Long>updateCategoria(Long id, CategoriaDTO cat);

    MessageResponseDto<Categoria>findById(Long id);

    MessageResponseDto<byte[]>getImage(Long idCategoria,MultipartFile imagen);
}
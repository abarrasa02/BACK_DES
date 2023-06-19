package com.example.AzentBACK.Provider;

import com.example.AzentBACK.DTO.ProductoDTO;
import com.example.AzentBACK.Entity.Producto;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductoProvider {

    MessageResponseDto<Long> addProducto(ProductoDTO productoDto);
    MessageResponseDto<String>deleteProducto(Long id);
    MessageResponseDto<Long>updateProducto(Long id,ProductoDTO productoDto);

    MessageResponseDto<List<Producto>>getProductos();

    MessageResponseDto<Producto>findById(Long id);

    MessageResponseDto<byte[]>getImage(Long id,MultipartFile image);

    MessageResponseDto<List<Producto>>filterByIdCategoria(Long id);



}

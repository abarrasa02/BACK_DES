package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.DTO.ProductoDTO;
import com.example.AzentBACK.Entity.Categoria;
import com.example.AzentBACK.Entity.Producto;
import com.example.AzentBACK.Provider.ProductoProvider;
import com.example.AzentBACK.Repository.CategoriaRepository;
import com.example.AzentBACK.Repository.ProductoRepository;
import com.example.AzentBACK.Utils.ImagenUtil;
import com.example.AzentBACK.Utils.MessageResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductoProviderImpl implements ProductoProvider {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;
    public ModelMapper modelMapper=new ModelMapper();
    public MessageResponseDto<Long>addProducto(ProductoDTO productoDto){
        try {
            Producto producto=modelMapper.map(productoDto,Producto.class);
            Optional<Categoria>cat=categoriaRepository.findById(productoDto.getCategoria());
            if(!cat.isPresent()){
                return MessageResponseDto.fail("No se ha encontrado la categoria");
            }
            producto.setCategoria(cat.get());
            Long contNombre=productoRepository.contNombre(producto.getNombre());
            if(contNombre>0){
                return MessageResponseDto.fail("No se puede añadir un producto con el mismo nombre");
            }
            producto=productoRepository.save(producto);
            return  MessageResponseDto.success(producto.getId());

        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido añadir un nuevo producto");
        }
    }
    public MessageResponseDto<String>deleteProducto(Long id){
        try {
            Optional<Producto> producto=productoRepository.findById(id);
            if(!producto.isPresent()){
                return MessageResponseDto.fail("No se ha podido eliminar el producto");
            }
            productoRepository.deleteById(id);
            return MessageResponseDto.success("Se ha eliminado el producto correctamente");
        }catch (Exception e){
            return MessageResponseDto.fail("Se ha producido un error al intentar eliminar el producto");
        }
    }

    public MessageResponseDto<Long>updateProducto(Long id,ProductoDTO productoDto){
        try {
            Producto producto=modelMapper.map(productoDto,Producto.class);
            Optional<Producto>producto1=productoRepository.findById(id);
            if (!producto1.isPresent()){
                return  MessageResponseDto.fail("No se ha encontrado el producto");
            }
            Optional<Categoria>cat=categoriaRepository.findById(productoDto.getCategoria());
            if(!cat.isPresent()){
                return MessageResponseDto.fail("No se ha encontrado la categoria");
            }
            producto.setCategoria(cat.get());
            producto.setId(id);
            producto=productoRepository.save(producto);
            return  MessageResponseDto.success(producto.getId());
        }catch (Exception e){
            log.error(e.getMessage());
            return MessageResponseDto.fail("No se ha podidod modificar el producto");
        }
    }



    public MessageResponseDto<List<Producto>>getProductos(){
        try {
            List<Producto>list=productoRepository.findAll();
            list.stream().forEach(producto -> producto.setImagen(ImagenUtil.decompressImage(producto.getImagen())));

            return MessageResponseDto.success(list);
        }catch (Exception e){
            return MessageResponseDto.fail("Error:No se han podido recoger los productos");
        }
    }


    public MessageResponseDto<Producto>findById(Long id){
        try {
            Optional<Producto>producto=productoRepository.findById(id);

            if (!producto.isPresent()){
                return  MessageResponseDto.fail("No se encontrado el producto");
            }
            Producto pro=producto.get();
            pro.setImagen((ImagenUtil.decompressImage(pro.getImagen())));
            return  MessageResponseDto.success(pro);

        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado el producto");
        }
    }
    public MessageResponseDto<List<Producto>>filterByIdCategoria(Long idCategoria){
        try{
            List<Producto>listPro=productoRepository.filter(idCategoria);
            listPro.stream().forEach(producto -> producto.setImagen(ImagenUtil.decompressImage(producto.getImagen())));
            return  MessageResponseDto.success(listPro);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha encontrado producto con esa categoria");
        }
    }
    public MessageResponseDto<byte[]>getImage(Long productoId, MultipartFile imagen){
        try {
            Optional<Producto>pro=productoRepository.findById(productoId);
            pro.get().setImagen(ImagenUtil.compressImage(imagen.getBytes()));
            productoRepository.save(pro.get());
            return MessageResponseDto.success(imagen.getBytes());
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al recoger la iamgen");
        }
    }
}
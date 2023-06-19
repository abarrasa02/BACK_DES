package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.DTO.CarritoDTO;
import com.example.AzentBACK.Entity.Carrito;
import com.example.AzentBACK.Provider.CarritoProvider;
import com.example.AzentBACK.Repository.CarritoRepository;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoProviderImpl implements CarritoProvider {

    @Autowired
    CarritoRepository carritoRepository;

    private ModelMapper modelMapper=new ModelMapper();

    public MessageResponseDto<String>addProductoCarrito(Carrito carrito){
        try {
             carritoRepository.save(carrito);
             return MessageResponseDto.success("Producto añadido al carrito correctamente");
        }catch (Exception e){
            return MessageResponseDto.fail("Producto añadido al carrito correctamente");
        }
    }

    public MessageResponseDto<String>deleteCarrito(Long id){
        try {
            carritoRepository.deleteById(id);
            return  MessageResponseDto.success("Se ha elimiando del carrito correctamente");
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha eliminado correctamente");
        }
    }
    public MessageResponseDto<List<Carrito>> saveModifiedItems(List<Carrito> carritoList) {
        try {
            return MessageResponseDto.success(carritoRepository.saveAll(carritoList));
        }catch (Exception e){
            return  MessageResponseDto.fail(e.getMessage());
        }

    }
    public MessageResponseDto<List<Carrito>>getByUsuario(Long id){
        try {
            List<Carrito>listPro=carritoRepository.findCarritoBy(id);
            if(listPro.isEmpty()){
                return  MessageResponseDto.fail("No hay productos añadidos al carrito");
            }
            return  MessageResponseDto.success(listPro);
        }catch (Exception e) {
            return MessageResponseDto.fail("Error al recoger los prodcutos del carrito");
        }
    }
}

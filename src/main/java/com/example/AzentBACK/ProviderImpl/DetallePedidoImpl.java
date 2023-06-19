package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.Entity.Carrito;
import com.example.AzentBACK.Entity.DetallePedido;
import com.example.AzentBACK.Provider.CarritoProvider;
import com.example.AzentBACK.Provider.DetallePedidoProvider;
import com.example.AzentBACK.Repository.CarritoRepository;
import com.example.AzentBACK.Repository.DetallePedidoRepository;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePedidoImpl implements DetallePedidoProvider {


    @Autowired
    DetallePedidoRepository detallePedidoRepository;

    @Autowired
    CarritoRepository carritoRepository;

    @Autowired
    CarritoProvider carritoProvider;
    public MessageResponseDto<Long> crearDetallePedido(DetallePedido detallePedido){
        try {
            DetallePedido detallePedido1=new DetallePedido();
           detallePedido1=detallePedidoRepository.save(detallePedido);
            carritoRepository.deleteAll(carritoProvider.getByUsuario(detallePedido.getPedido().getUsuario().getId()).getMessage());
          return  MessageResponseDto.success(detallePedido1.getId());
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al guardar el detalle pedido");
        }
    }

    public MessageResponseDto<List<DetallePedido>>getAll(){
        try {
            List<DetallePedido>detallePedidoList=new ArrayList<>();
            detallePedidoList=detallePedidoRepository.findAll();
            return MessageResponseDto.success(detallePedidoList);
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al recoger el detalle de los pedidos");
        }
    }
}

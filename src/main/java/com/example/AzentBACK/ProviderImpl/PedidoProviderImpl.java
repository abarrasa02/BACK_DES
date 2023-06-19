package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.Entity.Pedido;
import com.example.AzentBACK.Provider.PedidoProvider;
import com.example.AzentBACK.Repository.PedidoRepository;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PedidoProviderImpl implements PedidoProvider {

    @Autowired
    PedidoRepository pedidoRepository;
    public MessageResponseDto<Pedido>crearPedido(Pedido pedido){
        try {
            pedido.setFechaPedido(new Date());
            return  MessageResponseDto.success(pedidoRepository.save(pedido));
        }catch (Exception e){

            return  MessageResponseDto.fail("Error al crear el pedido");
        }
    }
    public MessageResponseDto<List<Pedido>>getAll(){
        try {
            return MessageResponseDto.success(pedidoRepository.findAll());
        }catch (Exception e){
            return MessageResponseDto.fail("Error al recoger los pedidos");
        }
    }

    public MessageResponseDto<List<Pedido>>getByIdUser(Long id){
        try {
            return MessageResponseDto.success(pedidoRepository.getPedidoById(id));
        }catch (Exception e){
            return MessageResponseDto.fail("No se han encontrado pedidos");
        }
    }
}

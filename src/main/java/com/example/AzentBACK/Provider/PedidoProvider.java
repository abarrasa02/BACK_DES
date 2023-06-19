package com.example.AzentBACK.Provider;

import com.example.AzentBACK.Entity.Pedido;
import com.example.AzentBACK.Utils.MessageResponseDto;

import java.util.List;

public interface PedidoProvider {
     MessageResponseDto<Pedido>crearPedido(Pedido pedido);
     public MessageResponseDto<List<Pedido>>getAll();
      MessageResponseDto<List<Pedido>>getByIdUser(Long id);
}

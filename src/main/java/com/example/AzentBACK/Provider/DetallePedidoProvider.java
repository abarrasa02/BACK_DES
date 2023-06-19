package com.example.AzentBACK.Provider;

import com.example.AzentBACK.Entity.DetallePedido;
import com.example.AzentBACK.Utils.MessageResponseDto;

import java.util.List;

public interface DetallePedidoProvider {

    MessageResponseDto<Long>crearDetallePedido(DetallePedido detallePedido);
    MessageResponseDto<List<DetallePedido>>getAll();
}

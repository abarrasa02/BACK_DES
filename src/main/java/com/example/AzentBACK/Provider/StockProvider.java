package com.example.AzentBACK.Provider;

import com.example.AzentBACK.Entity.Stock;
import com.example.AzentBACK.Utils.MessageResponseDto;

public interface StockProvider {

    public MessageResponseDto<String> restarStock(Long idProducto, int cantidad);
    MessageResponseDto<Boolean>comprobarStock(Long idProducto,int Cantidad);
    MessageResponseDto<String>modificarStock(Long idStock, Stock stock);

    MessageResponseDto<String>añadirStock(Long idProducto);
}

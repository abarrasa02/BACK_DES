package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.Entity.Producto;
import com.example.AzentBACK.Entity.Stock;
import com.example.AzentBACK.Provider.StockProvider;
import com.example.AzentBACK.Repository.ProductoRepository;
import com.example.AzentBACK.Repository.StockRepository;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class StockProviderImpl implements StockProvider {


    @Autowired
    StockRepository stockRepository;


    @Autowired
    ProductoRepository productoRepository;
    public MessageResponseDto<String>añadirStock(Long idProducto){
        try {
            Optional<Producto>pro=productoRepository.findById(idProducto);
            Stock s=new Stock();
            s.setCantidad(20);
            s.setProducto(pro.get());
            stockRepository.save(s);
            return MessageResponseDto.success("Se ha añadido correctamente el stock");
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido añadir el stock");
        }
    }

    public  MessageResponseDto<String>modificarStock(Long idStock,Stock stock){
            try {
                Optional<Stock>prodcutStock=stockRepository.findById(idStock);
                prodcutStock.get().setCantidad(stock.getCantidad());
                stockRepository.save(prodcutStock.get());
                return MessageResponseDto.success("Se ha modificado el stock correctamente");
            }catch (Exception e){
                return MessageResponseDto.fail("No se ha podido modificar- el stock");
            }
    }

    public MessageResponseDto<Boolean>comprobarStock(Long idProducto,int Cantidad){
        try {
            Stock s=stockRepository.findByIdProducto(idProducto);
            if(s.getCantidad()>Cantidad){
                return MessageResponseDto.success(true);
            }else{
                return MessageResponseDto.success(false);
            }
        }catch (Exception e){
            return MessageResponseDto.fail("Error al comprobar el stock");
        }
    }

    public MessageResponseDto<String>restarStock(Long idProducto,int cantidad){
        try {
            Stock s=stockRepository.findByIdProducto(idProducto);
            int cantidadActual=s.getCantidad()-cantidad;
            s.setCantidad(cantidadActual);
            stockRepository.save(s);
            return MessageResponseDto.success("Se ha actualizado el stock correctamente");
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha actualizado correctamente el stock");
        }
    }
}

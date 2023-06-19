package com.example.AzentBACK.Repository;

import com.example.AzentBACK.Entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository  extends JpaRepository<Stock, Long> {


    @Query("select s from  Stock s where s.producto.id=:idProducto")
    Stock findByIdProducto(@Param("idProducto") Long idProdcuto);
}

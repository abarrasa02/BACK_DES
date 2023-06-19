package com.example.AzentBACK.Repository;


import com.example.AzentBACK.Entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository  extends JpaRepository<Carrito,Long> {

    @Query("select c from Carrito  c where c.usuario.id=:idUser")
    List<Carrito>findCarritoBy(@Param("idUser")Long id);

    @Query("SELECT c from Carrito c where c.usuario.id=:idUser ")
    List<Carrito> buscarProdcutosByIdUser(@Param("idUser") Long idUsuario);



}

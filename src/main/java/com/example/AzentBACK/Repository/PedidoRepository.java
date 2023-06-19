package com.example.AzentBACK.Repository;

import com.example.AzentBACK.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido,Long> {


    @Query("select p from Pedido p where p.usuario.id=:idUser")
    List<Pedido>getPedidoById(@Param("idUser") Long id);
}

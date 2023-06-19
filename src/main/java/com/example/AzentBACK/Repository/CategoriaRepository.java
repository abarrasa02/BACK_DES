package com.example.AzentBACK.Repository;

import com.example.AzentBACK.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria,Long> {

    @Query("SELECT c from Categoria c where c.activo='S' OR c.activo is null ")
    List<Categoria> selectCatActivas();
}

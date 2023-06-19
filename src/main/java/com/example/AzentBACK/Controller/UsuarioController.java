package com.example.AzentBACK.Controller;

import com.example.AzentBACK.DTO.UsuarioDTO;
import com.example.AzentBACK.Entity.Usuario;
import com.example.AzentBACK.Provider.UsuarioProvider;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/usuario")

public class UsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(Usuario.class);
    @Autowired
    UsuarioProvider usuarioProvider;

    @PostMapping("/register")
    public MessageResponseDto<String> addUsuario(@RequestBody UsuarioDTO user){
        try {
            return usuarioProvider.addUsuario(user);
        }catch (Exception e){
            logger.info("ERROR:"+e.getMessage());
            return  MessageResponseDto.fail("Error al guardar el usuario");

        }
    }
    @GetMapping("/login")
    public  MessageResponseDto<Usuario>loginUser(@RequestParam String nombre,@RequestParam String contraseña){
        try {
            return usuarioProvider.loginUser(nombre,contraseña);
        }catch (Exception e){
            return MessageResponseDto.fail("Usuario o contraseña incorrectos");
        }
    }
    @GetMapping("/all")
    public  MessageResponseDto <List<Usuario>>getAllUsers(){
        try {
            return usuarioProvider.getAllUsers();
        }catch (Exception e){
            return  MessageResponseDto.fail("Error al recoger los usuarios correctamente");
        }
    }
}


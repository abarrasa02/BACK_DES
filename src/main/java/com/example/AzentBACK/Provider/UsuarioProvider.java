package com.example.AzentBACK.Provider;

import com.example.AzentBACK.DTO.UsuarioDTO;
import com.example.AzentBACK.Entity.Usuario;
import com.example.AzentBACK.Utils.MessageResponseDto;

import java.util.List;

public interface UsuarioProvider {

    MessageResponseDto<String> addUsuario(UsuarioDTO user);

    MessageResponseDto<Usuario>loginUser(String email,String contraseña);

    MessageResponseDto<List<Usuario>>getAllUsers();
}

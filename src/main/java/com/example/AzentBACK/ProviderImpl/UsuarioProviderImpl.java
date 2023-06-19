package com.example.AzentBACK.ProviderImpl;

import com.example.AzentBACK.DTO.UsuarioDTO;
import com.example.AzentBACK.Entity.Usuario;
import com.example.AzentBACK.Provider.UsuarioProvider;
import com.example.AzentBACK.Repository.UsuarioRepository;
import com.example.AzentBACK.Utils.MessageResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioProviderImpl implements UsuarioProvider {

    private ModelMapper modelMapper=new ModelMapper();

    @Autowired
    UsuarioRepository userRepository;

    public MessageResponseDto<String> addUsuario(UsuarioDTO userDto){
        Usuario user=modelMapper.map(userDto,Usuario.class);
        Long contEmail=userRepository.contEmail(userDto.getEmail());
        if(contEmail>0){
            return MessageResponseDto.fail("Ya hay un usuario registrado con ese email");
        }

        userRepository.save(user);
        return MessageResponseDto.success("Se ha añadido correctamente el usuario");
    }
    public MessageResponseDto<Usuario>loginUser(String usuario,String contraseña){
        try {
           Optional<Usuario> userAccess=userRepository.loginSuccess(usuario,contraseña);
            if(userAccess.isPresent()){
                return MessageResponseDto.success(userAccess.get());
            }else{
                return MessageResponseDto.fail("Usuario o contraseña incorrectos");
            }
        }catch (Exception e){
            return MessageResponseDto.fail("Ha ocurrido un error");
        }
    }
    public  MessageResponseDto<List<Usuario>>getAllUsers(){
        try {
            List<Usuario>listUsers=userRepository.findAll();
            return MessageResponseDto.success(listUsers);
        }catch (Exception e){
            return MessageResponseDto.fail("No se ha podido recoger los usuarios correctamente");
        }
    }
}
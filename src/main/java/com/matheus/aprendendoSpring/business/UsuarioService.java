package com.matheus.aprendendoSpring.business;

import com.matheus.aprendendoSpring.infrastructure.exceptions.ConflictException;
import com.matheus.aprendendoSpring.infrastructure.entity.Usuario;
import com.matheus.aprendendoSpring.infrastructure.exceptions.ResourceNotFoundException;
import com.matheus.aprendendoSpring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //injeção de dependência via construtor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    //salva usuarios
    public Usuario salvaUsuario(Usuario usuario) {
        try {
            emailExiste(usuario.getEmail());
           usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado: " + e.getCause());
        }
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado: " + e.getCause());
        }
    }
    //verifica no banco de dados se o email já existe
    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email)
        );
    }

    public void deletaUsuarioPorEmail(String email){
         usuarioRepository.deleteByEmail(email);
    }

}

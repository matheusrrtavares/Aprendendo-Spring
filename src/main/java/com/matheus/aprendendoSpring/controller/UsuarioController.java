package com.matheus.aprendendoSpring.controller;

import com.matheus.aprendendoSpring.business.UsuarioService;
import com.matheus.aprendendoSpring.controller.dtos.UsuarioDTO;
import com.matheus.aprendendoSpring.infrastructure.entity.Usuario;
import com.matheus.aprendendoSpring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController// A controller é a porta de entrada das informações da API
@RequestMapping("/usuario")// Responsável por apontar qual a URI da nossa controller
@RequiredArgsConstructor
public class UsuarioController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @PostMapping("/login")
    public String login (@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(), usuarioDTO.getSenha())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuarioPorEmail(@PathVariable String email){
         usuarioService.deletaUsuarioPorEmail(email);
            return ResponseEntity.ok().build();
    }


}

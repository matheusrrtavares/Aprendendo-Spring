package com.matheus.aprendendoSpring.infrastructure.repository;

import com.matheus.aprendendoSpring.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    @Transactional//Se qualquer parte da exclusão falhar, o Spring realiza um rollback, e o usuário que foi "apagado" no
        // início do método volta a existir, mantendo a consistência.
    void deleteByEmail(String email);

}

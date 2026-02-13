package com.matheus.aprendendoSpring.infrastructure.repository;

import com.matheus.aprendendoSpring.infrastructure.entity.Telefone;
import com.matheus.aprendendoSpring.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}

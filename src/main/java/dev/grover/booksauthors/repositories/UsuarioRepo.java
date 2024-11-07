package dev.grover.booksauthors.repositories;

import dev.grover.booksauthors.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String nombre);
}

package dev.grover.booksauthors.repositories;

import dev.grover.booksauthors.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentoRepo extends JpaRepository<Documento, Long> {
}

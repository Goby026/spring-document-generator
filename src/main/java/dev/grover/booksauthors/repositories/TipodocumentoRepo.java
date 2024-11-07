package dev.grover.booksauthors.repositories;

import dev.grover.booksauthors.domain.Tipodocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipodocumentoRepo extends JpaRepository<Tipodocumento, Long> {
}

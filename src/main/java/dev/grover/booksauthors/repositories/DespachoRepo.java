package dev.grover.booksauthors.repositories;

import dev.grover.booksauthors.domain.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachoRepo extends JpaRepository<Despacho, Long> {
}

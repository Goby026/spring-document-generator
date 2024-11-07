package dev.grover.booksauthors.repositories;

import dev.grover.booksauthors.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {
}

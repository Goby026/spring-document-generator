package dev.grover.booksauthors.services;

import dev.grover.booksauthors.domain.Cargo;
import dev.grover.booksauthors.repositories.CargoRepo;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CargoService implements DAOService<Cargo> {

    private final CargoRepo repo;

    public CargoService(CargoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Cargo registrar(Cargo p) throws Exception {
        p.setEstado(1);
        return this.repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repo.deleteById(id);
    }

    @Override
    public Cargo obtener(Long id) throws Exception {
        return this.repo.findById(id).get();
    }

    @Override
    public List<Cargo> listar() throws Exception {
        return this.repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}

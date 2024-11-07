package dev.grover.booksauthors.services;

import dev.grover.booksauthors.domain.Despacho;
import dev.grover.booksauthors.repositories.DespachoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespachoService implements DAOService<Despacho> {

    private final DespachoRepo repo;

    public DespachoService(DespachoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Despacho registrar(Despacho p) throws Exception {
        return this.repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repo.deleteById(id);
    }

    @Override
    public Despacho obtener(Long id) throws Exception {
        return this.repo.findById(id).get();
    }

    @Override
    public List<Despacho> listar() throws Exception {
        return this.repo.findAll();
    }
}

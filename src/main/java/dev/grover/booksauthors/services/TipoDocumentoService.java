package dev.grover.booksauthors.services;

import dev.grover.booksauthors.domain.Tipodocumento;
import dev.grover.booksauthors.repositories.TipodocumentoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDocumentoService implements DAOService<Tipodocumento> {

    private final TipodocumentoRepo repo;

    public TipoDocumentoService(TipodocumentoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Tipodocumento registrar(Tipodocumento p) throws Exception {
        return this.repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repo.deleteById(id);
    }

    @Override
    public Tipodocumento obtener(Long id) throws Exception {
        return this.repo.findById(id).get();
    }

    @Override
    public List<Tipodocumento> listar() throws Exception {
        return this.repo.findAll();
    }
}

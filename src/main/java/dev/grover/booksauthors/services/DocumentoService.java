package dev.grover.booksauthors.services;

import dev.grover.booksauthors.domain.Documento;
import dev.grover.booksauthors.repositories.DocumentoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService implements DAOService<Documento> {

    private final DocumentoRepo repo;

    public DocumentoService(DocumentoRepo repo) {
        this.repo = repo;
    }

    @Override
    public Documento registrar(Documento p) throws Exception {
        return this.repo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.repo.deleteById(id);
    }

    @Override
    public Documento obtener(Long id) throws Exception {
        return this.repo.findById(id).get();
    }

    @Override
    public List<Documento> listar() throws Exception {
        return this.repo.findAll();
    }
}

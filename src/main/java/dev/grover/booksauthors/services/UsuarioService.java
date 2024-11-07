package dev.grover.booksauthors.services;

import dev.grover.booksauthors.domain.Usuario;
import dev.grover.booksauthors.repositories.UsuarioRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public class UsuarioService implements DAOService<Usuario>, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepo usuarioRepo;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepo usuarioRepo) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrar(Usuario p) throws Exception {
        p.setPassword(this.passwordEncoder.encode(p.getPassword()));
        return this.usuarioRepo.save(p);
    }

    @Override
    public void eliminar(Long id) throws Exception {
        this.usuarioRepo.deleteById(id);
    }

    @Override
    public Usuario obtener(Long id) throws Exception {
        return this.usuarioRepo.findById(id).get();
    }

    @Override
    public List<Usuario> listar() throws Exception {
        return this.usuarioRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepo.findByUsername(username);

        return usuario.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("usuario No Encontrado "+ username));
    }
}

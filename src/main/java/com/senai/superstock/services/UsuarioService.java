package com.senai.superstock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senai.superstock.entities.Usuario;
import com.senai.superstock.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario create(Usuario usuario) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        Usuario usuarioCriado = repository.save(usuario);
        return usuarioCriado;
    }

    public Usuario read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Usuario> list() {
        return repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        if (repository.existsById(usuario.getId())) {
            return repository.save(usuario);
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByCpf(username);
        if (usuario != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .password(usuario.getSenha())
                    .username(usuario.getUsername())
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    }
}

package br.com.zupacademy.priscila.mercadolivre.utils.security;

import br.com.zupacademy.priscila.mercadolivre.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AutenticacaoService implements UserDetailsService {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = manager.createQuery("select u from Usuario u where u.login = :login", Usuario.class)
				.setParameter("login", username)
				.getSingleResult();

		if (usuario != null) {
			return usuario;
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}
}

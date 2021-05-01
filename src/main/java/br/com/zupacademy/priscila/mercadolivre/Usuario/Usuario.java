package br.com.zupacademy.priscila.mercadolivre.Usuario;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataDeCadastro;

    public Usuario(String login, @Valid @NotNull SenhaLimpa senhaLimpa) {
        this.login = login;
        this.senha =  senhaLimpa.hash();
    }

}

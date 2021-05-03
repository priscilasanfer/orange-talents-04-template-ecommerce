package br.com.zupacademy.priscila.mercadolivre.usuario;

import br.com.zupacademy.priscila.mercadolivre.utils.validation.UniqueValue;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @UniqueValue(targetClass = Usuario.class, field = "login")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public NovoUsuarioRequest(@NotBlank @Email String login,
                              @NotBlank @Length(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new SenhaLimpa(senha));
    }
}

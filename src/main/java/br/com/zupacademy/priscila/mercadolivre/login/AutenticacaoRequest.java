package br.com.zupacademy.priscila.mercadolivre.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutenticacaoRequest {

    @Email
    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public AutenticacaoRequest(@Email  @NotBlank String login,
                               @NotBlank String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}

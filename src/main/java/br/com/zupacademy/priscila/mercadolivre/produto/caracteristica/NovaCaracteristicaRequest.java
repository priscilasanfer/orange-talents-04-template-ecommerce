package br.com.zupacademy.priscila.mercadolivre.produto.caracteristica;

import br.com.zupacademy.priscila.mercadolivre.produto.Produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class NovaCaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public NovaCaracteristicaRequest(@NotBlank String nome,
                                     @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NovaCaracteristicaRequest)) return false;
        NovaCaracteristicaRequest that = (NovaCaracteristicaRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Caracteristica toModel(@NotNull @Valid Produto produto) {
        return new Caracteristica(nome, descricao, produto);
    }
}

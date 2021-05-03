package br.com.zupacademy.priscila.mercadolivre.produto;

import br.com.zupacademy.priscila.mercadolivre.categoria.Categoria;
import br.com.zupacademy.priscila.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.priscila.mercadolivre.usuario.Usuario;
import br.com.zupacademy.priscila.mercadolivre.utils.validation.ExistsId;
import br.com.zupacademy.priscila.mercadolivre.utils.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    @UniqueValue(targetClass = Produto.class, field = "nome")
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @Size(min = 3)
    @Valid
    private List<NovaCaracteristicaRequest> caracteristicas = new ArrayList<>();

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ExistsId(required = true, targetClass = Categoria.class, field = "id")
    private Long categoriaId;

    private Long usuarioId;

    public NovoProdutoRequest(@NotBlank String nome,
                              @NotNull @Positive BigDecimal valor,
                              @NotNull @PositiveOrZero Integer estoque,
                              @NotBlank @Size(max = 1000) String descricao,
                              @NotNull Long categoriaId,
                              List<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = estoque;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public List<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(EntityManager manager, Usuario usuario) {
        @NotNull Categoria categoria = manager.find(Categoria.class, categoriaId);

        Produto produto = new Produto(this.nome,
                this.valor,
                this.quantidade,
                this.descricao,
                categoria,
                usuario,
                caracteristicas);

        return produto;

    }

    public Set<String> buscaPorCaracteristicasIguais() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultados = new HashSet<>();

        for (NovaCaracteristicaRequest caracteristica : caracteristicas) {
            String nome = caracteristica.getNome();
            if (!nomesIguais.add(nome)){
                resultados.add(nome);
            }
        }
        return resultados;
    }
}

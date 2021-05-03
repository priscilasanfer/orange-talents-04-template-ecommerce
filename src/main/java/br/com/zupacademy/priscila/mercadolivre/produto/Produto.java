package br.com.zupacademy.priscila.mercadolivre.produto;

import br.com.zupacademy.priscila.mercadolivre.categoria.Categoria;
import br.com.zupacademy.priscila.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.priscila.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.priscila.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer estoque;

    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @Valid
    private Categoria categoria;

    @ManyToOne
    @Valid
    private Usuario usuario;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataDeCadastro;

    public Produto(String nome,
                   BigDecimal valor,
                   Integer estoque,
                   String descricao,
                   Categoria categoria,
                   Usuario usuario,
                   Collection<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.estoque = estoque;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
        this.caracteristicas.addAll(caracteristicas
                .stream()
                .map(caracteristca -> caracteristca.toModel(this))
                .collect(Collectors.toSet()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}

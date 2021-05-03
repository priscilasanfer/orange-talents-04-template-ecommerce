package br.com.zupacademy.priscila.mercadolivre.categoria;

import br.com.zupacademy.priscila.mercadolivre.utils.validation.ExistsId;
import br.com.zupacademy.priscila.mercadolivre.utils.validation.UniqueValue;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(targetClass = Categoria.class, field = "nome")
    private String nome;

    @ExistsId(targetClass = Categoria.class, field = "id")
    private Long categoriaId;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categoria toModel(EntityManager manager) {
        Categoria categoria = new Categoria(this.nome);

        if(categoriaId != null){
            Categoria categoriaMae= manager.find(Categoria.class, categoriaId);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}

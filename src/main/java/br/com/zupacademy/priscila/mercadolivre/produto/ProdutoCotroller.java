package br.com.zupacademy.priscila.mercadolivre.produto;

import br.com.zupacademy.priscila.mercadolivre.usuario.Usuario;
import br.com.zupacademy.priscila.mercadolivre.utils.validation.ProibeCaracteristicaComNomeIgualValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoCotroller {

    @PersistenceContext
    private EntityManager manager;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    @PostMapping
    @Transactional
    public void salvar(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuario){
        Produto novoProduto = request.toModel(manager, usuario);
        manager.persist(novoProduto);
    }
}

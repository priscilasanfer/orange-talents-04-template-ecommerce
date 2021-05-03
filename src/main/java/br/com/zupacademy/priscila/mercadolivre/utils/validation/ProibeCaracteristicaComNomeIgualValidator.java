package br.com.zupacademy.priscila.mercadolivre.utils.validation;

import br.com.zupacademy.priscila.mercadolivre.produto.NovoProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeCaracteristicaComNomeIgualValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NovoProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) return ;
        NovoProdutoRequest request  = (NovoProdutoRequest) target;
        Set<String> nomesIguais = request.buscaPorCaracteristicasIguais();
        if (!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null,"Tem caracteristicas iguais");
        }
    }
}

package br.com.zupacademy.priscila.mercadolivre.utils.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {
    @PersistenceContext
    private EntityManager manager;

    private String field;
    private Class<?> targetClass;
    private boolean required;

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        field = constraintAnnotation.field();
        targetClass = constraintAnnotation.targetClass();
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (!required) return true;

        return  manager.createQuery("select count(t) = 1 from "
                + targetClass.getName() + " t where " + field + " = :valor", Boolean.class )
                .setParameter("valor", value)
                .getSingleResult();
    }
}

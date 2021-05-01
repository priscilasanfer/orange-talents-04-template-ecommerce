package br.com.zupacademy.priscila.mercadolivre.utils.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValueValidator implements ConstraintValidator <UniqueValue, Object>{

    @PersistenceContext
    private EntityManager manager;

    private String field;
    private Class<?> targetClass;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        field = constraintAnnotation.field();
        targetClass = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return  manager.createQuery("select count(t) < 1 from "
                + targetClass.getName() + " t where " + field + " = :valor", Boolean.class )
                .setParameter("valor", value)
                .getSingleResult();
    }
}

package br.com.zupacademy.priscila.mercadolivre.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistsIdValidator.class})
public @interface ExistsId {
    Class<?> targetClass();

    String field();

    boolean required() default false;

    String message() default "O dado informado no campo {0} não existe, por favor revise e tente novamente.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

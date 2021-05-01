package br.com.zupacademy.priscila.mercadolivre.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueValueValidator.class})
public @interface UniqueValue {

    Class<?> targetClass();

    String field();

    String message() default "O valor do campo {0} já está em uso.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

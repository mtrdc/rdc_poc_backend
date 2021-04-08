package tr.com.turkcell.poc.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(regexp = "^[0-9]{10}$")
@Constraint(validatedBy = {})
@Documented
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface PhoneNumber {

    String message() default "Wrong phone number format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

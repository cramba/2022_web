package de.hsrm.mi.web.projekt.validierung;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=BuntValidator.class)

@Documented
public @interface Bunt {
    String message() default "Wert ist nicht Bunt genug";

    //String value();
    
}

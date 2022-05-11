@Target({ElementType.FIELD})
@Rentation(RentationPolicy.RUNTIME)
@Constraint(validatedBy=BuntValidator.class)

@Documented
public @interface Bunt {
    String message() default "Wert ist nicht Bunt genug";

}

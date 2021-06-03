package BLL.Validators;

/**
 * The interface that defines the validation method
 * @param <T> The type of the class whose objects will be validated
 */
public interface Validator<T> {
    /**
     * Validation method
     * @param t The object to be validated
     */
    public void validate(T t);
}

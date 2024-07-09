package ttl.larku.solutions.exceptions;

/**
 * @author whynot
 */
public class ExWrapper<T> {
    public T value;
    public Exception exception;

    private ExWrapper(T value, Exception e) {
        this.value = value;
        this.exception = e;
    }

    public static <X> ExWrapper<X> ofValue(X value) {
        return new ExWrapper<X>(value, null);
    }

    public static <X> ExWrapper<X> ofError(Exception e) {
        return new ExWrapper<X>(null, e);
    }
}

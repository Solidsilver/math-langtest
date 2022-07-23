package languages.jmath;

public class MismatchDimensionException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MismatchDimensionException() {
        super();
    }

    public MismatchDimensionException(String s) {
        super(s);
    }
}

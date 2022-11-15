package exceptions;

public class UnknownProductException extends PresentException {
    public UnknownProductException() {super("Product ID is unknown");}
}

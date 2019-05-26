package umcarro.Controllers.Exceptions;

public class OpcaoInvalida extends Throwable {
    public OpcaoInvalida() { super(); }
    public OpcaoInvalida(String ex) { super(ex); }
    public OpcaoInvalida(String message, Throwable cause) { super(message, cause); }
    public OpcaoInvalida(Throwable cause) { super(cause); }
}

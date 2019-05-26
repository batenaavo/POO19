package umcarro.Controllers.Exceptions;

public class NaoTemPedidos extends Throwable {
    public NaoTemPedidos() { super(); }
    public NaoTemPedidos(String ex) { super(ex); }
    public NaoTemPedidos(String message, Throwable cause) { super(message, cause); }
    public NaoTemPedidos(Throwable cause) { super(cause); }
}

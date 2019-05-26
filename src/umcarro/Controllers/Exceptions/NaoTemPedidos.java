package umcarro.Controllers.Exceptions;

import java.io.Serializable;

public class NaoTemPedidos extends Throwable implements Serializable {
    private static final long serialVersionUID = 8656285456855493751L;

    public NaoTemPedidos() { super(); }
    public NaoTemPedidos(String ex) { super(ex); }
    public NaoTemPedidos(String message, Throwable cause) { super(message, cause); }
    public NaoTemPedidos(Throwable cause) { super(cause); }
}

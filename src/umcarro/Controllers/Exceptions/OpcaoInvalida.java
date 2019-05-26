package umcarro.Controllers.Exceptions;

import java.io.Serializable;

public class OpcaoInvalida extends Throwable implements Serializable {
    private static final long serialVersionUID = 5612294119517104813L;

    public OpcaoInvalida() { super(); }
    public OpcaoInvalida(String ex) { super(ex); }
    public OpcaoInvalida(String message, Throwable cause) { super(message, cause); }
    public OpcaoInvalida(Throwable cause) { super(cause); }
}

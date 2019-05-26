package umcarro.Controllers.Exceptions;

import java.io.Serializable;

public class SemVeiculosDisponiveis extends Exception implements Serializable {
    private static final long serialVersionUID = -7971067448084283593L;

    public SemVeiculosDisponiveis() { super(); }
    public SemVeiculosDisponiveis(String ex) { super(ex); }
    public SemVeiculosDisponiveis(String message, Throwable cause) { super(message, cause); }
    public SemVeiculosDisponiveis(Throwable cause) { super(cause); }
}

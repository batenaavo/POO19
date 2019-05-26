package umcarro.Controllers.Exceptions;

import java.io.Serializable;

public class DadosDeAcessoInvalidos extends Exception implements Serializable {
    private static final long serialVersionUID = 4622139951805246161L;

    public DadosDeAcessoInvalidos() { super(); }
    public DadosDeAcessoInvalidos(String ex) { super(ex); }
    public DadosDeAcessoInvalidos(String message, Throwable cause) { super(message, cause); }
    public DadosDeAcessoInvalidos(Throwable cause) { super(cause); }
}

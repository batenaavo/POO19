package umcarro.Controllers.Exceptions;

public class DadosDeAcessoInvalidos extends Exception {
    public DadosDeAcessoInvalidos() { super(); }
    public DadosDeAcessoInvalidos(String ex) { super(ex); }
    public DadosDeAcessoInvalidos(String message, Throwable cause) { super(message, cause); }
    public DadosDeAcessoInvalidos(Throwable cause) { super(cause); }
}

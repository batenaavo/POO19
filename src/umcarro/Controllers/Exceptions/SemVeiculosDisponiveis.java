package umcarro.Controllers.Exceptions;

public class SemVeiculosDisponiveis extends Exception {
    public SemVeiculosDisponiveis() { super(); }
    public SemVeiculosDisponiveis(String ex) { super(ex); }
    public SemVeiculosDisponiveis(String message, Throwable cause) { super(message, cause); }
    public SemVeiculosDisponiveis(Throwable cause) { super(cause); }
}

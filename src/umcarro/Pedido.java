package umcarro;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Pedido {
    private Integer id;
    private Integer nifCliente;
    private Integer nifProprietario;
    private Double cordXDest;
    private Double cordYDest;
    private String matricula;
    private String preferencia;
    private Boolean pendente;
    private Boolean porClassificar;


    public Pedido(Integer nifCliente, Integer nifProprietario, Double cordXDest, Double cordYDest, String matricula,
                  String preferencia,
                  Boolean pendente, Boolean porClassificar) {
        this.id = null;
        this.nifCliente = nifCliente;
        this.nifProprietario = nifProprietario;
        this.cordXDest = cordXDest;
        this.cordYDest = cordYDest;
        this.matricula = matricula;
        this.preferencia = preferencia;
        this.pendente = pendente;
        this.porClassificar = porClassificar;
    }

    public Pedido(){
        this.id = null;
        this.nifProprietario = null;
        this.nifCliente = null;
        this.cordXDest = null;
        this.cordYDest = null;
        this.matricula = null;
        this.preferencia = null;
        this.pendente = false;
        this.porClassificar = false;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(Integer nif) {
        this.nifCliente = nif;
    }

    public Integer getNifProprietario() {
        return nifProprietario;
    }

    public void setNifProprietario(Integer nifProprietario) {
        this.nifProprietario = nifProprietario;
    }

    public Double getCordXDest() {
        return cordXDest;
    }

    public void setCordXDest(Double cordXDest) {
        this.cordXDest = cordXDest;
    }

    public Double getCordYDest() {
        return cordYDest;
    }

    public void setCordYDest(Double cordYDest) {
        this.cordYDest = cordYDest;
    }


    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public Boolean getPendente() {
        return pendente;
    }

    public void setPendente(Boolean porClassificar) {
        this.porClassificar = porClassificar;
    }

    public Boolean getPorClassificar() {
        return porClassificar;
    }

    public void setPorClassificar(Boolean porClassificar) {
        this.porClassificar = porClassificar;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nifCliente=" + nifCliente +
                ", nifProprietario=" + nifProprietario +
                ", cordXDest=" + cordXDest +
                ", cordYDest=" + cordYDest +
                ", matricula='" + matricula + '\'' +
                ", preferencia='" + preferencia + '\'' +
                ", pendente=" + pendente +
                ", porClassificar=" + porClassificar +
                '}';
    }
}

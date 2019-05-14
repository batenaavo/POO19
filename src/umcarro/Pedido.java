package umcarro;

public class Pedido {
    private Integer id;
    private Integer nif;
    private Double cordXDest;
    private Double cordYDest;
    private String matricula;
    private String preferencia;
    private Boolean pendente;


    public Pedido(Integer nif, Double cordXDest, Double cordYDest, String matricula,
                  String preferencia,
                  Boolean pendente) {
        this.id = null;
        this.nif = nif;
        this.cordXDest = cordXDest;
        this.cordYDest = cordYDest;
        this.matricula = matricula;
        this.preferencia = preferencia;
        this.pendente = pendente;
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

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
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

    public void setPendente(Boolean pendente) {
        this.pendente = pendente;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", nif=" + nif +
                ", cordXDest=" + cordXDest +
                ", cordYDest=" + cordYDest +
                ", matricula='" + matricula + '\'' +
                ", preferencia='" + preferencia + '\'' +
                ", pendente=" + pendente +
                '}';
    }
}

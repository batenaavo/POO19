package umcarro;

public class Pedido {
    private Integer nif;
    private Double cordXDest;
    private Double cordYDest;
    private String tipoCombust;
    private String preferencia;

    public Pedido(Integer nif, Double cordXDest, Double cordYDest, String tipoCombust, String preferencia) {
        this.nif = nif;
        this.cordXDest = cordXDest;
        this.cordYDest = cordYDest;
        this.tipoCombust = tipoCombust;
        this.preferencia = preferencia;
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

    public String getTipoCombust() {
        return tipoCombust;
    }

    public void setTipoCombust(String tipoCombust) {
        this.tipoCombust = tipoCombust;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }
}

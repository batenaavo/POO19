package umcarro;


public class Veiculo {

    private String tipo;
    private String marca;
    private String matricula;
    private Integer nif;
    private Integer velocidadeMedia;
    private Double precoPorKm;
    private Double consumoPorKm;
    private Double autonomia;
    private Double cordX;
    private Double cordY;
    private Double rating;

    public Veiculo() {
        this.tipo = null;
        this.marca = null;
        this.matricula = null;
        this.nif = null;
        this.velocidadeMedia = null;
        this.precoPorKm = null;
        this.consumoPorKm = null;
        this.autonomia = null;
        this.cordX = null;
        this.cordY = null;
        this.rating = null;
    }

    public Veiculo(String tipo, String marca, String matricula, Integer nif, Integer velocidadeMedia,
                   Double precoPorKm, Double consumoPorKm, Double autonomia, Double cordX, Double cordY, Double rating) {
        this.tipo = tipo;
        this.marca = marca;
        this.matricula = matricula;
        this.nif = nif;
        this.velocidadeMedia = velocidadeMedia;
        this.precoPorKm = precoPorKm;
        this.consumoPorKm = consumoPorKm;
        this.autonomia = autonomia;
        this.cordX = cordX;
        this.cordY = cordY;
        this.rating = rating;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    public Integer getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(Integer velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public Double getPrecoPorKm() {
        return precoPorKm;
    }

    public void setPrecoPorKm(Double precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    public Double getConsumoPorKm() {
        return consumoPorKm;
    }

    public void setConsumoPorKm(Double consumoPorKm) {
        this.consumoPorKm = consumoPorKm;
    }

    public Double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Double autonomia) {
        this.autonomia = autonomia;
    }

    public Double getCordX() {
        return cordX;
    }

    public void setCordX(Double cordX) {
        this.cordX = cordX;
    }

    public Double getCordY() {
        return cordY;
    }

    public void setCordY(Double cordY) {
        this.cordY = cordY;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    /*public void abastecerCarro(VeiculoString matricula, Double autonomia) {
            Veiculo v =
            setAutonomia(autonomia);
        }
    */
    @Override
    public java.lang.String toString() {
        return "Veiculo{" +
                "tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", matricula='" + matricula + '\'' +
                ", nif=" + nif +
                ", velocidadeMedia=" + velocidadeMedia +
                ", precoPorKm=" + precoPorKm +
                ", consumoPorKm=" + consumoPorKm +
                ", autonomia=" + autonomia +
                ", cordX=" + cordX +
                ", cordY=" + cordY +
                '}' + "\n";
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;


public class Veiculo {

    private String matricula;
    private String modelo;
    private String marca;
    private String tipo;
    private Integer eficiencia;
    private Integer precoPorKm;
    private Float cordX;
    private Float cordY;


    public Veiculo() {
        this.matricula = null;
        this.modelo = null;
        this.marca = null;
        this.tipo = null;
        this.eficiencia = null;
        this.precoPorKm = null;
        this.cordX = null;
        this.cordY = null;
    }

    public Veiculo(String matricula, String modelo, String marca, String tipo, Integer eficiencia, Integer precoPorKm, Float cordX, Float cordY) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.eficiencia = eficiencia;
        this.precoPorKm = precoPorKm;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    public Veiculo(Veiculo c){
        this.matricula = c.getMatricula();
        this.modelo = c.getModelo();
        this.marca = c.getMarca();
        this.tipo = c.getTipo();
        this.eficiencia = c.getEficiencia();
        this.precoPorKm = c.getPrecoPorKm();
        this.cordX = c.getCordX();
        this.cordY = c.getCordY();

    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(Integer eficiencia) {
        this.eficiencia = eficiencia;
    }

    public Integer getPrecoPorKm() {
        return precoPorKm;
    }

    public void setPrecoPorKm(Integer precoPorKm) {
        this.precoPorKm = precoPorKm;
    }

    public Float getCordX() {
        return cordX;
    }

    public void setCordX(Float cordX) {
        this.cordX = cordX;
    }

    public Float getCordY() {
        return cordY;
    }

    public void setCordY(Float cordY) {
        this.cordY = cordY;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", tipo='" + tipo + '\'' +
                ", eficiencia=" + eficiencia +
                ", precoPorKm=" + precoPorKm +
                ", cordX=" + cordX +
                ", cordY=" + cordY +
                '}';
    }
}

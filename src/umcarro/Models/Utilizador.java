/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro.Models;
import java.util.Date;

public class Utilizador {
    private String nome;
    private Integer nif;
    private String username;
    private String morada;
    private Date dataNasc;
    private String password;
    private Double rating;
    private Integer ratingCounter;

    public Utilizador(String nome, Integer nif, String username, String morada, Date dataNasc, String password) {
        this.nome = nome;
        this.nif = nif;
        this.username = username;
        this.morada = morada;
        this.dataNasc = dataNasc;
        this.password = password;
        this.rating = 100.00;
        this.ratingCounter = 0;
    }

    public Utilizador(Utilizador c) {
        this.nome = c.getNome();
        this.nif = c.getNif();
        this.username = c.getUsername();
        this.morada = c.getMorada();
        this.dataNasc = c.getDataNasc();
        this.password = c.getPassword();
        this.rating = c.getRating();
        this.ratingCounter = c.getRatingCounter();
    }

    public void addRating (Double rating){
        this.rating = ((this.rating*this.ratingCounter) + rating)/(++this.ratingCounter);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public Integer getNif() {
        return nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getRatingCounter() {
        return ratingCounter;
    }

    public void setRatingCounter(Integer ratingCounter) {
        this.ratingCounter = ratingCounter;
    }

    public boolean validaPassword(String pwd) {
        return (this.password.equals(pwd));
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "nome='" + nome + '\'' +
                ", nif=" + nif +
                ", username='" + username + '\'' +
                ", morada='" + morada + '\'' +
                ", dataNasc=" + dataNasc +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                '}';
    }
}


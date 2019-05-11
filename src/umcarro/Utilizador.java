/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;
import java.util.Date;

public class Utilizador {
    private String nome;
    private Integer nif;
    private String username;
    private String morada;
    private Date dataNasc;
    private String password;

    public Utilizador(String nome, Integer nif, String username, String morada, Date dataNasc, String password) {
        this.nome = nome;
        this.nif = nif;
        this.username = username;
        this.morada = morada;
        this.dataNasc = dataNasc;
        this.password = password;
    }

    public Utilizador(Utilizador c){
        this.nome = c.getNome();
        this.nif = c.getNif();
        this.username = c.getUsername();
        this.morada = c.getMorada();
        this.dataNasc = c.getDataNasc();
        this.password = c.getPassword();
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

    public boolean validaPassword(String pwd){
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
                '}';
    }
}



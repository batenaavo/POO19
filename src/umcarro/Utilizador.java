/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;
import java.util.Date;

public class Utilizador {

    private String username;
    private String nome;
    private Date dataNasc;
    private String password;
    private String morada;

    public Utilizador(String username, String nome, String password, String address, int dataNasc) {
        this.username = null;
        this.nome = null;
        this.password = null;
        this.morada = null;
        this.dataNasc = new Date();
    }

    public Utilizador(String username, String nome, String password, String address, Date dataNasc) {
        this.username = username;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNasc = dataNasc;
    }

    public Utilizador(Utilizador c){
        this.username = c.getUsername();
        this.nome = c.getNome();
        this.password = c.getPassword();
        this.morada = c.getMorada();
        this.dataNasc = c.getDataNasc();
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
}



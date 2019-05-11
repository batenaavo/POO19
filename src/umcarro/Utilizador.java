/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;

public class Utilizador {

    private String username;
    private String nome;
    private Integer nif;
    //private String password;
    private String morada;

    public Utilizador(String username, String nome/* String password*/, String morada, int nif) {
        this.username = null;
        this.nome = null;
      //  this.password = null;
        this.morada = null;
        this.nif = null;
    }

    public Utilizador(String username, String nome/*, String password*/, String morada, Integer nif) {
        this.username = username;
        this.nome = nome;
        //this.password = password;
        this.morada = morada;
        this.nif = nif;
    }

    public Utilizador(Utilizador c){
        this.username = c.getUsername();
        this.nome = c.getNome();
      /*  this.password = c.getPassword(); */
        this.morada = c.getMorada();
        this.nif = c.getnif();
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

    public Integer getnif() {
        return nif;
    }

    public void setnif(Integer nif) {
        this.nif = nif;
    }

    /*
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
*/
    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    /*
    public boolean validaPassword(String pwd){
        return (this.password.equals(pwd));
    }
*/
    @Override
    public String toString() {
        return  "username='" + username + '\'' + ",\n" +
                "nome='" + nome + '\'' + ",\n" +
                "nif=" + nif + ",\n" +
                "morada='" + morada + '\'';
    }
}



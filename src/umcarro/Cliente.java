/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;


import java.util.Date;

public class Cliente extends Utilizador{

    private Float cordX;
    private Float cordY;


    public Cliente(String nome, Integer nif, String username, String morada, Date dataNasc, String password, Float cordX, Float cordY) {
        super(nome, nif, username, morada, dataNasc, password);
        this.cordX = cordX;
        this.cordY = cordY;
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
        return "Cliente{ " +
                super.toString() +
                " cordX=" + cordX +
                ", cordY=" + cordY +
                '}';
    }
}

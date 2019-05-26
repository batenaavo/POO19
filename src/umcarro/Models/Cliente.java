/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro.Models;


import java.util.Date;

public class Cliente extends Utilizador {

    private Double cordX;
    private Double cordY;


    public Cliente(String nome, Integer nif, String username, String morada, Date dataNasc, String password, Double cordX, Double cordY) {
        super(nome, nif, username, morada, dataNasc, password);
        this.cordX = cordX;
        this.cordY = cordY;
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


    @Override
    public String toString() {
        return "Cliente{ " +
                super.toString() +
                " cordX=" + cordX +
                ", cordY=" + cordY +
                '}';
    }
}

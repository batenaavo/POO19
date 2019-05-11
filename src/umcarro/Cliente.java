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


    public Cliente(String username, String nome/*String password*/, String morada, Integer nif,  Float cordX, Float cordY) {
        super(username, nome, morada, nif);
        this.cordX = cordX;
        this.cordY = cordY;
    }


    @Override
    public String toString() {
        return  "Cliente{" + "\n" +
                super.toString() + "\n" +
                ", cordX=" + cordX + "\n" +
                ", cordY=" + cordY + "\n" +
                '}';
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
}

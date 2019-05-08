/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;


import java.util.Date;

public class Cliente extends Utilizador{



    private Integer nCartaoCred;
    private String validadeCartaoCred;
    private Integer codSeguranca;
    private Float cordX;
    private Float cordY;


    public Cliente(String username, String nome, String password, String address, Date dataNasc, Integer nCartaoCred, String validadeCartaoCred, Integer codSeguranca, Float cordX, Float cordY) {
        super(username, nome, password, address, dataNasc);
        this.nCartaoCred = nCartaoCred;
        this.validadeCartaoCred = validadeCartaoCred;
        this.codSeguranca = codSeguranca;
        this.cordX = cordX;
        this.cordY = cordY;
    }

    @Override
    public String toString() {
        return  "Cliente{" + "\n" +
                super.toString() + "\n" +
                "nCartaoCred=" + nCartaoCred + "\n" +
                ", validadeCartaoCred='" + validadeCartaoCred + '\'' + "\n" +
                ", codSeguranca=" + codSeguranca + "\n" +
                ", cordX=" + cordX + "\n" +
                ", cordY=" + cordY + "\n" +
                '}';
    }

    public Integer getnCartaoCred() {
        return nCartaoCred;
    }

    public void setnCartaoCred(Integer nCartaoCred) {
        this.nCartaoCred = nCartaoCred;
    }

    public String getValidadeCartaoCred() {
        return validadeCartaoCred;
    }

    public void setValidadeCartaoCred(String validadeCartaoCred) {
        this.validadeCartaoCred = validadeCartaoCred;
    }

    public Integer getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(Integer codSeguranca) {
        this.codSeguranca = codSeguranca;
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

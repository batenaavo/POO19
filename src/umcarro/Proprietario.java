/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;

import java.util.ArrayList;
import java.util.Date;


public class Proprietario extends Utilizador{

    private ArrayList<Veiculo> veiculos;
    private Integer userRating;
    private ArrayList<Historico> historicoArray;

    public Proprietario(String username, String nome, String password, String address, Date dataNasc, ArrayList<Veiculo> veiculos, Integer userRating, ArrayList<Historico> historicoArray) {
        super(username, nome, password, address, dataNasc);
        this.veiculos = veiculos;
        this.userRating = userRating;
        this.historicoArray = historicoArray;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public ArrayList<Historico> getHistoricoArray() {
        return historicoArray;
    }

    public void setHistoricoArray(ArrayList<Historico> historicoArray) {
        this.historicoArray = historicoArray;
    }
}

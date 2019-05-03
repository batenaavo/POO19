/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Proprietario extends Utilizador{

    private HashMap<String,Veiculo> veiculos;
    private Integer userRating;
    private ArrayList<Historico> historicoArray;

    public Proprietario(String username, String nome, String password, String address, Date dataNasc, HashMap<String,Veiculo> veiculos, Integer userRating, ArrayList<Historico> historicoArray) {
        super(username, nome, password, address, dataNasc);
        this.veiculos = veiculos;
        this.userRating = userRating;
        this.historicoArray = historicoArray;
    }

    public Proprietario(String username, String nome, String password, String address, Date dataNasc) {
        super(username, nome, password, address, dataNasc);
        this.veiculos = new HashMap<>();
        this.userRating = 0;
        this.historicoArray = new ArrayList<>();
    }

    public HashMap<String, Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(HashMap<String, Veiculo> veiculos) {
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

    public void setVeiculo(String matricula, Veiculo veiculos) {
        this.veiculos.put(matricula, veiculos);
    }

    @Override
    public String toString() {
        String veiculos = "";
        for(Veiculo v: this.veiculos.values()) {
            veiculos += v.toString() + "\n\t\t";
        }

        String historico = "";
        for(Historico h: this.historicoArray) {
            historico += h.toString() + "\n\t\t";
        }

        return "Proprietario {" + "\n" +
                super.toString() + "\n" +
                "Veiculos: \n\t\t" + veiculos +
                "rating: " + this.userRating + "\n" +
                "historico: \n\t\t" + historico +
                "}";
    }
}

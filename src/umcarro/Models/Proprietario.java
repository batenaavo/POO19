package umcarro.Models;

import umcarro.Controllers.Pedidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Proprietario extends Utilizador {

    private HashMap<String, Veiculo> veiculos;
    private ArrayList<Pedidos> pedidosArray;


    public Proprietario(String nome, Integer nif, String username, String morada, Date dataNasc, String password) {
        super(nome, nif, username, morada, dataNasc, password);
        this.veiculos = new HashMap<>();
        this.pedidosArray = new ArrayList<>();
    }




    public HashMap<String, Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(HashMap<String, Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Pedidos> getPedidosArray() {
        return pedidosArray;
    }

    public void setPedidosArray(ArrayList<Pedidos> pedidosArray) {
        this.pedidosArray = pedidosArray;
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
        for(Pedidos h: this.pedidosArray) {
            historico += h.toString() + "\n\t\t";
        }

        return "Proprietario {" + "\n" +
                super.toString() + "\n" +
                "Veiculos: \n\t\t" + veiculos +
                "historico: \n\t\t" + historico +
                "}";
    }
}
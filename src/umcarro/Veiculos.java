package umcarro;

import java.util.ArrayList;
import java.util.List;

public class Veiculos {
    private ArrayList<Veiculo> veiculos;

    public Veiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Veiculos() {
        this.veiculos = new ArrayList<>();
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void addVeiculo (Veiculo v){
        this.veiculos.add(v);
    }

    public Veiculo veiculoMaisProximo(Float x, Float y){
        Veiculo aux = null;
        Double dfinal = 99999999999999999999999999999999999.99;
        for(Veiculo v: this.veiculos) {
           Double dist = Math.sqrt((v.getCordY() - y) * (v.getCordY() - y) + (v.getCordX() - x) * (v.getCordX() - x));
           if(dist<=dfinal){
               aux = v;
               dfinal = dist;
            }
        }
        return aux;
    }

    public List<String> veiculoComAutonomiaDesejada (Integer x) {
        int i=0;
        List<String> ret = new ArrayList<String>();
        for (Veiculo v: this.veiculos) {
            if (x <= v.getAutonomia()) {
                ret.add( (++i) + ":" + " " + v.getMatricula() + " " + v.getMarca() + " " + (v.getAutonomia()) + " " + v.getPrecoPorKm()+ "€");
            }
        }
        return ret;
    }
}

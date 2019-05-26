package umcarro.Controllers;

import umcarro.Controllers.Exceptions.SemVeiculosDisponiveis;
import umcarro.Models.Veiculo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Veiculos {

    private ArrayList<Veiculo> veiculos;

    public Veiculo getVeiculoByMatricula (String matricula){
        for (Veiculo v : this.veiculos){
            if (v.getMatricula().equals(matricula)) {
                return v;
            }
        }
        return null;
    }

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




    public Veiculos getVeiculosDeProprietário(Integer x){
        Veiculos veiculosByProp = new Veiculos();
        for (Veiculo v : this.veiculos){
            if (v.getNif().equals(x)) {
                veiculosByProp.addVeiculo(v);
            }
        }
        return veiculosByProp;
    }

    public Veiculo getVeiculoByProprietario(Integer x){
        for (Veiculo v: this.veiculos) {
           if (v.getNif().equals(x)){
               return v;
           }
        }
        return null;
    }

    public Veiculos veiculoComAutonomiaDesejada (Double x) {
        Veiculos ret = new Veiculos();
        for (Veiculo v: this.veiculos) {
            if (x <= v.getAutonomia()) {
                ret.addVeiculo(v);
            }
        }
        return ret;
    }

    public Veiculos veiculoAlcancavel (Double raio, Double x1, Double x2) {
        Veiculos veiculosDisp = new Veiculos();
        for (Veiculo v : this.veiculos) {
            if (raio >= distanciaEntrePontos(x1, v.getCordX(), x2, v.getCordY())) {
                veiculosDisp.addVeiculo(v);
            }
        }
        return veiculosDisp;
    }


        public Double distanciaEntrePontos (Double x1, Double y1, Double x2, Double y2){
            Double dist = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) + (y2 - y1));
            return dist;
        }

   public Veiculos veiculosCapazesDeRealizarViagem (Double x1, Double x2){
        Veiculos veiculosCap = new Veiculos();
        for (Veiculo v : this.veiculos) {
            Double distancia = distanciaEntrePontos(x1, v.getCordX(), x2, v.getCordY());
            if (distancia <= v.getAutonomia()) {
                veiculosCap.addVeiculo(v);
            }
        } return veiculosCap;
   }

   public Veiculos veiculosByCombustivel (String combustivel) {
        Veiculos ret = new Veiculos();
        for (Veiculo v: this.veiculos)
            if (v.getTipo().equals(combustivel))
                ret.addVeiculo(v);
         return  ret;
   }

    public Veiculo veiculoMaisProximo (Double locX, Double locY){
        Veiculo ve = null;
        if (!this.veiculos.isEmpty()){
            ve = this.veiculos.get(0);
            for (Veiculo v: this.veiculos) {
                if (distanciaEntrePontos(locX, v.getCordX(), locY, v.getCordY()) < distanciaEntrePontos(locX, ve.getCordX(), locY, ve.getCordY()))
                    ve = v;
            }
        }
        return ve;
    }

   public Veiculo veiculoMaisBarato (Double tripX, Double tripY){
        Veiculo ve = null;
        if (!this.veiculos.isEmpty()){
            ve = this.veiculos.get(0);
            for (Veiculo v: this.veiculos) {
                if (v.getConsumoPorKm()*distanciaEntrePontos(tripX, v.getCordX(), tripY, v.getCordY()) < ve.getConsumoPorKm()*distanciaEntrePontos(tripX, ve.getCordX(),tripY,ve.getCordY()))
                    ve = v;
            }
        }
        return ve;
   }
    @Override
    public String toString() {
        return "Veiculos{" +
                "veiculos=" + veiculos +
                '}';
    }

    public Veiculo selectVeiculoAutonomia(Double autonomiaDes, Veiculos veiculosRelevantes) {
        Veiculos veiculosCapazes = veiculosRelevantes.veiculoComAutonomiaDesejada(autonomiaDes);
        boolean chosen2= false;
        while (!chosen2) {
            Veiculo vSelec = selectMatricula( veiculosCapazes);
            if (vSelec!=null)
                return vSelec;
        }
        return null;
    }

    public Veiculo selectVeiculoEspecifico( Veiculos veiculosRelevantes) {
        boolean chosen= false;
        while (!chosen) {
            Veiculo vSelec = selectMatricula( veiculosRelevantes);
            if (vSelec!=null)
                return vSelec;
        }
        return null;
    }


    public Veiculo selectMatricula(Veiculos veiculosCapazes){
        Scanner scanner = new Scanner(System.in);
        System.out.print(veiculosCapazes);
        System.out.println("Indique o a matrícula do veículo desejado:");
        String matriculaDes = scanner.nextLine();
        Veiculo vSelec = veiculosCapazes.getVeiculoByMatricula(matriculaDes);
        while (vSelec == null) {
            System.out.println("Opção Inválida. Volte a indicar a matrícula.");
            matriculaDes = scanner.nextLine();
            vSelec = veiculosCapazes.getVeiculoByMatricula(matriculaDes);
        }
        return  vSelec;
    }

    public Veiculo selectMaisBaratoAlcancavel(Double raio, Double locX, Double locY, Double tripX, Double tripY, Veiculos veiculosRelevantes) throws SemVeiculosDisponiveis {
        if (veiculosRelevantes != null) {
            veiculosRelevantes = veiculosRelevantes.veiculoAlcancavel(raio, locX, locY);
            Veiculo vSelecionado = veiculosRelevantes.veiculoMaisBarato(tripX, tripY);
            return vSelecionado;
        } else throw new SemVeiculosDisponiveis();
    }

    public Veiculo selectMaisBarato( Double tripX, Double tripY, Veiculos veiculosRelevantes) throws SemVeiculosDisponiveis {
        if (veiculosRelevantes != null) {
            Veiculo veiculoMaisBarato = veiculosRelevantes.veiculoMaisBarato(tripX, tripY);
            return veiculoMaisBarato;
        } else throw new SemVeiculosDisponiveis();
    }

    public Veiculo selectMaisProximo(Double locX, Double locY, Veiculos veiculosRelevantes) throws SemVeiculosDisponiveis {
        if (veiculosRelevantes != null) {
            Veiculo veiculoMaisProximo = veiculosRelevantes.veiculoMaisProximo(locX, locY);
            return veiculoMaisProximo;
        } else throw new SemVeiculosDisponiveis();
    }
    public void addRatingVeiculo (String matricula, Double nota){
        Veiculo v = getVeiculoByMatricula(matricula);
        for (Veiculo c : this.veiculos) {
            if (c.equals(v)) {
                c.setRating(nota);
            }
        }
    }


}

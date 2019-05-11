package umcarro;

import java.util.HashMap;

public class Proprietarios {
    private HashMap<Proprietario, Veiculos> proprietarios;

    public Proprietarios(){
        this.proprietarios = new HashMap<>();
    }

    public Proprietarios(HashMap<Proprietario, Veiculos> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public void addProprietario(Proprietario p) {
        Veiculos v = new Veiculos();
        this.proprietarios.put(p,v);
    }

    public Proprietario getProprietarioByNif(Integer nif) {
        for(Proprietario p: this.proprietarios.keySet())
            if(p.getNif()==nif)
                return p;
        return null;
    }

}

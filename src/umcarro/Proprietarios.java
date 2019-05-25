package umcarro;

import java.util.HashMap;

public class Proprietarios {
    private HashMap<Integer, Proprietario> proprietarios;

    public Proprietarios(){
        this.proprietarios = new HashMap<>();
    }

    public Proprietario getProprietariosByUser(String user){
        for (Proprietario c : this.proprietarios.values())
            if (c.getUsername().equals(user))
                return c;
        return null;
    }

//
    public void addRatingToProp (Integer nif, Double nota){
        if (this.proprietarios.get(nif) != null)
        this.proprietarios.get(nif).addRating(nota);
    }

    public Proprietarios(HashMap<Integer, Proprietario> proprietarios) {
        this.proprietarios = proprietarios;
    }

    public void addProprietario(Proprietario p) {
        this.proprietarios.put(p.getNif(),p);
    }

    public Proprietario getProprietarioByNif(Integer nif) {
        return this.proprietarios.get(nif);
    }

}

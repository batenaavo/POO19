package umcarro.Controllers;

import umcarro.Models.Cliente;

import java.io.Serializable;
import java.util.HashMap;

public class Clientes {

    private HashMap<Integer, Cliente> clientes;



    public Clientes(HashMap<Integer, Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getClienteByUser(String user){
        for (Cliente c : this.clientes.values())
            if (c.getUsername().equals(user))
                return c;
        return null;
    }

    public Clientes() {
        this.clientes = new HashMap<>();

    }

    public void addCliente(Cliente cliente){
        this.clientes.put(cliente.getNif(),cliente);
    }

    public Cliente getClienteByNif (Integer nif) {
        return this.clientes.get(nif);
    }

     public void addRating (Integer nif, Double nota){

         if (this.clientes.get(nif) != null)
        this.clientes.get(nif).addRating(nota);
     }

    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(HashMap<Integer, Cliente> clientes) {
        this.clientes = clientes;
    }






}

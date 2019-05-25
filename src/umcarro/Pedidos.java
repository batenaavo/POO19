package umcarro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Pedidos {

    private ArrayList<Pedido> pedidos;
    private Integer pedidosCounter;


    public Pedidos() {
        this.pedidos = new ArrayList<>();
        this.pedidosCounter = 0;
    }

    public ArrayList<Pedido> getPedidos() {
        return this.pedidos;
    }


    public Pedidos getPedidosDeCliente (Integer nif){
        Pedidos ps = new Pedidos();
        for (Pedido p: this.pedidos){
            if (p.getNifCliente().equals(nif) && p.getPendente()) {
                ps.addNewPedido(p);
            }
        }
        return null;
    }

    public Pedidos getPedidosDeProp (Integer nif){
        Pedidos ps = new Pedidos();
        for (Pedido p: this.pedidos){
            if (p.getNifProprietario().equals(nif) && p.getPendente()) {
                ps.addNewPedido(p);
            }
        }
        return null;
    }

    public Pedido addNewPedido(Pedido p) {
        p.setId(this.pedidosCounter++);
        this.pedidos.add(p);
        return p;
    }

    public Pedido selectPedidoById (Integer id){
        for (Pedido p: this.pedidos) {
            if (p.getId().equals(id)) {
                return p;
            }
        } return null;
    }

    public void removePedido (Pedido p){
        if (this.pedidos.contains(p)) {
            this.pedidos.remove(p);
        }
    }

    public Pedidos getPedidosPorClassificarDeCliente (Cliente c) throws NaoTemPedidos {
        Pedidos porClass = new Pedidos();
        for (Pedido p : this.pedidos) {
            if (p.getPorClassificar()) {
                porClass.addNewPedido(p);
            } return porClass;
        } throw new NaoTemPedidos();
    }
    //FIXME
    @Override
    public String toString() {
        return "Pedidos{" +
                "pedidos=" + pedidos +
                ", pedidosCounter=" + pedidosCounter +
                '}';
    }
}
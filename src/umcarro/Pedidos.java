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

    public Pedidos getPedidosDeProp (Integer nif){
        Pedidos ps = new Pedidos();
        for (Pedido p: this.pedidos){
            if (p.getNif().equals(nif) && p.getPendente()) {
                ps.addNewPedido(p);
            }
        }
    }

    public Pedido reviewPedidos (Pedido p){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indique o id do pedido que pretende aceitar");

    }

    public Pedido addNewPedido(Pedido p) {
        p.setId(this.pedidosCounter++);
        this.pedidos.add(p);
        return p;
    }

    @Override
    public String toString() {
        return "Pedidos{" +
                "pedidos=" + pedidos +
                ", pedidosCounter=" + pedidosCounter +
                '}';
    }
}
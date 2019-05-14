package umcarro;

import java.util.ArrayList;
import java.util.HashMap;

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
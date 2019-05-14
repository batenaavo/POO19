package umcarro;

import java.util.ArrayList;

public class Pedidos {

    private ArrayList<Pedido> pedidos;

    public Pedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }


    public Pedidos(){
        this.pedidos = new ArrayList<>();
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
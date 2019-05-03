package umcarro;

public class Historico {

    private Cliente cliente;
    private Veiculo veiculo;
    private Integer kmsPercorridos;
    private Integer valorAluguer;
    private Integer velocidadeMedia;

    public Historico(Cliente cliente, Veiculo veiculo) {
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.kmsPercorridos = null;
        this.valorAluguer = null;
        this.velocidadeMedia = null;
    }

    /**public completaHistorico(Integer kmsPercorridos, Integer valorAluguer, Integer velocidadeMedia){

        this.kmsPercorridos = kmsPercorridos;
        this.valorAluguer = valorAluguer;
        this.velocidadeMedia = velocidadeMedia;

    }
*/
}

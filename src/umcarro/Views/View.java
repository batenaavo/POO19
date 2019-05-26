package umcarro.Views;

import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String menuInicial() {
        System.out.println("Bem-Vindo ao UMCarroJá ");
        System.out.println("Selecione a sua opção:\n" +
                "1:Login\n" +
                "2:Registo\n" +
                "3:Sair");
        return this.scanner.nextLine();
    }

    public void printMensagem(String m) {
        System.out.println(m);
    }

    public String opcoesPedidos() {
        System.out.println("Escolha uma das seguintes opções" +
                "1: Aceitar.\n" +
                "2: Recusar.\n"+
                "3: Re-selecionar pedido.\n" +
                "4: Voltar ao menu anterior.\n");
        return this.scanner.nextLine();
    }

    public String opcoesProprietario() {
        System.out.println("Selecione a sua opção\n" +
                "1: Abastecer Carro(alterar autonomia).\n" +
                "2: Alterar preço por km de Veículo. (km).\n" +
                "3: Adicionar Carro.\n" +
                "4: Ver propostas de cliente.\n" +
                "5: Logout.\n");
        return this.scanner.nextLine();
    }

    public Integer perferenciaCompostivel() {
        System.out.println("Indique a sua preferência de combustível\n" +
                "1: Gasolina. \n" +
                "2: Hibrido. \n" +
                "3: Electrico.");
        return this.scanner.nextInt();
    }

    public Integer opcoesAluger() {
        System.out.println("Selecione a sua opção\n" +
                "1: Escolher veículo mais barato. \n" +
                "2: Escolher veiculo mais próximo. \n" +
                "3: Escolher veículo mais barato alcançável a pé.\n" +
                "4: Escolher veículo específico.\n" +
                "5: Ver Veículos com taxa diária mais baratas.\n");
        return this.scanner.nextInt();
    }

    public String opcoesCliente() {
        System.out.println("Escolha uma das seguintes opcoes:\n" +
                "1: Classificar Proprietario/Veiculo das Última Viagem.\n"+
                "2: Alugar Carro.\n"+
                "3: Logout.\n");
        return this.scanner.nextLine();
    }

    public String userTypeSelect() {
        System.out.println("Selecione a sua opção:\n" +
                "1:Cliente\n" +
                "2:Proprietário\n");
        return this.scanner.nextLine();
    }


    public String getString() {
        return this.scanner.nextLine();
    }

    public Integer getInteger() {
        return this.scanner.nextInt();
    }

    public Double getDouble() {
        return this.scanner.nextDouble();
    }


}

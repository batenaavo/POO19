/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;



import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class UMCarro {

    private Cliente cliente;
    private Proprietario proprietario;
    private Clientes clientes;
    private Proprietarios proprietarios;
    private Veiculos veiculos;
    private Pedidos pedidos;

    public UMCarro() {
        this.proprietarios = new Proprietarios();
        this.clientes = new Clientes();
        this.pedidos = new Pedidos();
        this.veiculos = new Veiculos();
    }


    private void DataDump(){
        try{
            FileReader fr = new FileReader("C:\\Users\\Teste 1\\Documents\\GitHub\\poo2019\\POO19\\src\\umcarro" +
                    "\\input.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null) {
                String[] aux = line.split(":");
                String[] toAdd = aux[1].split(",");
                switch (aux[0]){
                    case "NovoProp":
                        Proprietario p = new Proprietario(toAdd[0],Integer.parseInt(toAdd[1]),toAdd[2],toAdd[3],
                                new Date(1994,12,16),"pass");
                        this.proprietarios.addProprietario(p);
                        break;
                    case "NovoCliente":
                        Cliente c = new Cliente(toAdd[0],Integer.parseInt(toAdd[1]),toAdd[2],toAdd[3],new Date(1994,
                                12,16),"pass",Double.parseDouble(toAdd[4]),Double.parseDouble(toAdd[5]));
                        this.clientes.addCliente(c);
                        break;
                    case "NovoCarro":
                        Veiculo v = new Veiculo(toAdd[0],toAdd[1],toAdd[2],Integer.parseInt(toAdd[3]),
                                Integer.parseInt(toAdd[4]),Double.parseDouble(toAdd[5]),Double.parseDouble(toAdd[6]),
                                Integer.parseInt(toAdd[7]),Double.parseDouble(toAdd[8]),Double.parseDouble(toAdd[9]));
                       this.veiculos.addVeiculo(v);
                        break;
                    case "Aluguer":
                         Pedido a = new Pedido(Integer.parseInt(toAdd[0]),Double.parseDouble(toAdd[1]),
                                 Double.parseDouble(toAdd[2]),toAdd[3],toAdd[4]);
                         break;
                    case "Classificar":
                        if (toAdd[0].length() > 8){
                            this.clientes.addRating(Integer.parseInt(toAdd[0]),Double.parseDouble(toAdd[1]));
                            this.proprietarios.addRating(Integer.parseInt(toAdd[0]),Double.parseDouble(toAdd[1]));
                        } else {
                            Veiculo g = this.veiculos.getVeiculoByMatricula(toAdd[0]);
                            this.proprietarios.addRating(g.getNif(),Double.parseDouble(toAdd[1]));
                            /* mudar rating de proprietarios para carros.*/
                        }
                        break;

                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Utilizador(email): ");
        String user = scanner.nextLine();
        System.out.print("Password:  ");
        String pwd = scanner.nextLine();

        if (this.clientes.getClienteByUser(user)!=null && this.clientes.getClienteByUser(user).validaPassword(pwd)) {
            cliente = this.clientes.getClienteByUser(user);
            this.menuCliente();
        } else if (this.proprietarios.getProprietariosByUser(user)!=null && this.proprietarios.getProprietariosByUser(user).validaPassword(pwd)) {
            proprietario = this.proprietarios.getProprietariosByUser(user);
            this.menuProprietario();
        } else {
            System.out.println("Username e/ou Password Inválidos. ");
        }

    }

    private void registo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione a sua opção:\n" +
                "1:Cliente\n" +
                "2:Proprietário\n");
        String userType = scanner.nextLine();
        if (!userType.equals("1") && !userType.equals("2")){
            System.out.println("Opção Inválida! ");
            registo();
        } else {
            System.out.print("Utilizador(email): ");
            String user = scanner.nextLine();
            System.out.print("Password:  ");
            String pwd = scanner.nextLine();
            System.out.print("Número de Idenficação Fiscal");
            Integer nif = scanner.nextInt();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Morada: ");
            String morada = scanner.nextLine();
            System.out.print("Data de Nascimento (mm/dia/ano): ");
            String dataNasc = scanner.nextLine();

            if (userType.equals("1")) {
                System.out.print("Localização X");
                Double cordX = scanner.nextDouble();
                System.out.print("Localização Y");
                Double cordY = scanner.nextDouble();
                Cliente c = new Cliente(nome,nif,user,morada,new Date(dataNasc),pwd, cordX, cordY);
                if (this.clientes.getClienteByUser(user)== null) {
                    this.clientes.addCliente(c);
                    System.out.println("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    System.out.println("Utilizador já existente! ");
                }
            } else {
                Proprietario p = new Proprietario(nome,nif,user,morada,new Date(dataNasc),pwd);
                if (this.proprietarios.getProprietariosByUser(user)== null) {
                    this.proprietarios.addProprietario(p);
                    System.out.println("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    System.out.println("Utilizador já existente! ");
                }
            }
        }
    }
    private void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indique a sua localização x:");
        Double locX = scanner.nextDouble();
        System.out.println("Indique a sua localização y:");
        Double locY = scanner.nextDouble();
        System.out.println("Indique a localização x para onde quer viajar:");
        Double tripX = scanner.nextDouble();
        System.out.println("Indique a localização x para onde quer viajar:");
        Double tripY = scanner.nextDouble();
        this.cliente.setCordX(locX);
        this.cliente.setCordY(locY);
        System.out.println("Selecione a sua opção\n" +
                "1: Ver Lista de Veiculos disponíveis e compatíveis com a sua viagem. \n" +
                "2: Ver Veiculo mais próximo e compatível com a sua viagem. \n" +
                "3: Ver Veículos com autonomia desejada (km).\n" +
                "4: Ver Veículos mais alcançáveis a pé.\n" +
                "5: Ver Veículos com taxa diária mais baratas.\n");
        String optionSelected = scanner.nextLine();
        switch (optionSelected) {
            case "1":
                menuCliente();
            case "2":
                Veiculo v = this.veiculos.veiculoMaisProximo(this.cliente.getCordX(),this.cliente.getCordY());
                System.out.println(v.toString());
                break;
            case "3":
                autonomiaSelector(veiculos);
            case "4":
                break;
        }
    }

    private void menuProprietario() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione a sua opção\n" +
                "1: Abastecer Carro(alterar autonomia). \n" +
                "2: Sinalizar Veículo Disponível. \n" +
                "3: Alterar preço por km de Veículo. (km).\n" +
                "4: Ver propostas de cliente.\n" +
                "5: Registar quanto custou a viagem.\n");
        String optionSelected = scanner.nextLine();
        switch(optionSelected){
            case "1":
                menuCliente();
            case "2":

        }
    }


    private void alterarAutonomia() {

    }




    private boolean confirmationSelector(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Confirma a sua seleção? (Y/N)?");
        String resp = scanner.nextLine();
        if (resp.equals("Y") || resp.equals("y")) {
            return true;
        } else if (resp.equals("N") || resp.equals("n")){
            return false;
        } else {
            System.out.println("Resposta Inválida");
            return false;
        }
    }

    private void autonomiaSelector(Veiculos listaVeiculos){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o mínimo de autonomia desejado em kms.");
        Integer autonomia = scanner.nextInt();
        List<String> aux = veiculos.veiculoComAutonomiaDesejada(autonomia);
        System.out.print(aux);
        System.out.println("Indique o número correspondente ao veículo que deseja selecionar: ");
        String nVeiculo = scanner.nextLine();
        String carSelected = "";
        for(String s : aux) {
            if (nVeiculo.equals(s.split(" ")[0])) {
                carSelected = s;
                break;
            }
        }
        if (confirmationSelector()) {
            System.out.println("Pedido enviado, aguarde resposta.");
        } else {
            autonomiaSelector(listaVeiculos);
        }
    }

    private void menuVeiculos(){

    }



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UMCarro app = new UMCarro();
        System.out.println("Bem-Vindo ao UMCarroJá ");

        app.DataDump(); //Inicialização de Dados

        boolean sair = false;

        while(!sair){
            System.out.println("Bem-Vindo ao UMCarroJá ");
            System.out.println("Selecione a sua opção:\n" +
                    "1:Login\n" +
                    "2:Registo\n" +
                    "3:Sair");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    app.login();
                    break;
                case "2":
                    app.registo();
                    break;
                case "3":
                    sair = true;
                    System.out.println("Obrigado!");
                    break;
                default:
                    System.out.println("Opção não encontrada!");
                    break;
            }
        }
    }
    
}

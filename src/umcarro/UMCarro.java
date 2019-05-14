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
                                Double.parseDouble(toAdd[7]),Double.parseDouble(toAdd[8]),Double.parseDouble(toAdd[9]));
                       this.veiculos.addVeiculo(v);
                        break;
                    case "Aluguer":
                        Integer nif = Integer.parseInt(toAdd[0]);
                         Pedido a = new Pedido(nif,Double.parseDouble(toAdd[1]),
                                 Double.parseDouble(toAdd[2]),null,toAdd[4],false);
                         String tipoComb = toAdd[3];
                         if (a.getPreferencia().equals("MaisBarato")) {
                             Veiculo v1 = veiculos.selectMaisBarato(a.getCordXDest(),a.getCordYDest(),
                                     this.veiculos.veiculosByCombustivel(tipoComb));
                             a.setMatricula(v1.getMatricula());
                             this.pedidos.addNewPedido(a);
                         } else if (a.getPreferencia().equals("MaisPerto")) {
                             Cliente cli = clientes.getClienteByNif(nif);
                             Veiculo v2 = veiculos.selectMaisProximo(cli.getCordX(), cli.getCordY(),
                                     this.veiculos.veiculosByCombustivel(tipoComb));
                             a.setMatricula(v2.getMatricula());
                             this.pedidos.addNewPedido(a);
                         }
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
        System.out.print(this.veiculos);
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
        System.out.println("Indique a sua localização actual x:");
        Double locX = scanner.nextDouble();
        System.out.println("Indique a sua localização actual y:");
        Double locY = scanner.nextDouble();
        System.out.println("Indique a localização x para onde quer viajar:");
        Double tripX = scanner.nextDouble();
        System.out.println("Indique a localização y para onde quer viajar:");
        Double tripY = scanner.nextDouble();
        System.out.println("Indique a sua preferência de combustível\n" +
                "1: Gasolina. \n" +
                "2: Hibrido. \n" +
                "3: Electrico.");
        Integer combustívelPref = scanner.nextInt();
        Veiculos veiculosRelevantes = this.veiculos;
        switch (combustívelPref) {
            case 3:
                veiculosRelevantes = veiculosRelevantes.veiculosByCombustivel("Electrico");
                break;
            case 1:
                veiculosRelevantes = veiculosRelevantes.veiculosByCombustivel("Gasolina");
                break;
            case 2:
                veiculosRelevantes = veiculosRelevantes.veiculosByCombustivel("Hibrido");
                break;
        }
        this.cliente.setCordX(locX);
        this.cliente.setCordY(locY);
        veiculosRelevantes = veiculosRelevantes.veiculosCapazesDeRealizarViagem(tripX, tripY);
        System.out.println("Selecione a sua opção\n" +
                "1: Escolher veículo mais barato. \n" +
                "2: Escolher veiculo mais próximo. \n" +
                "3: Escolher veículo mais barato alcançável a pé.\n" +
                "4: Escolher veículo específico.\n" +
                "5: Ver Veículos com taxa diária mais baratas.\n");
        Integer optionSelected = scanner.nextInt();
        switch (optionSelected) {
            case 1:
                Veiculo veiculoMaisBarato = veiculos.selectMaisBarato(tripX, tripY, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisBarato", veiculoMaisBarato);
                break;
            case 2:
                Veiculo veiculoMaisProximo = veiculos.selectMaisProximo(locX, locY, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisPerto", veiculoMaisProximo);
                break;
            case 3:
                Veiculo vSelecionado = veiculos.selectMaisBaratoAlcancavel(scanner,locX, locY,
                    tripX, tripY,
                        veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisBaratoAlcançavel", vSelecionado);
                break;
            case 4:
                Veiculo vSelec1 = veiculos.selectVeiculoEspecifico(veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "VeiculoEspecifico", vSelec1);
                break;
            case 5:
                Veiculo vSelec2 = veiculos.selectVeiculoAutonomia(scanner, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "VeiculoAutonomia", vSelec2);
                break;

        }
    }



    public boolean confirmationVehicle(Integer nif,  Double cordXDest, Double cordYDest,
                                           String preferencia, Veiculo v){
        Scanner scanner = new Scanner(System.in);
        System.out.print(v);
        System.out.println("Confirma a sua seleção? (Y/N)?");
        String resp = scanner.nextLine();
        if (resp.equals("Y") || resp.equals("y")) {
            Pedido p = new Pedido(nif, cordXDest, cordYDest, v.getMatricula(), preferencia, true);
            this.pedidos.addNewPedido(p);
            return true;
        } else if (resp.equals("N") || resp.equals("n")){
            return false;
        } else {
            System.out.println("Resposta Inválida");
            return false;
        }
    }


    private void menuProprietario() {
        this.veiculos = this.veiculos.getveiculoByProprietário(this.proprietario.getNif());
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
                System.out.println("Indique a matrícula do carro que pretende abastecer:\n");
               /* String carroAbastecer = scanner.nextLine();
                for (Veiculo v: this.veiculos) {
                    carroAbastecer =
                }
                if (carroAbastecer.equals(v.getMatricula())) {

                }
*/
            case "2":

        }
    }




/*
    private void autonomiaSelector(Veiculos listaVeiculos){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o mínimo de autonomia desejado em kms.");
        Double autonomia = scanner.nextDouble();
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
*/


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UMCarro app = new UMCarro();

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

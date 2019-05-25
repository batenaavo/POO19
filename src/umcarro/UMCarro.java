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
            /*TODO droppar as primeiras linhas */
            FileReader fr = new FileReader("src/logsPOO_carregamentoInicial.bak");
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
                                Double.parseDouble(toAdd[7]),Double.parseDouble(toAdd[8]),Double.parseDouble(toAdd[9]),0.0);
                       this.veiculos.addVeiculo(v);
                        break;
                    case "Aluguer":
                        Integer nif = Integer.parseInt(toAdd[0]);
                         Pedido a = new Pedido(null,nif,Double.parseDouble(toAdd[1]),
                                 Double.parseDouble(toAdd[2]),null,toAdd[4],false,false);
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
                            this.proprietarios.addRatingToProp(Integer.parseInt(toAdd[0]),Double.parseDouble(toAdd[1]));
                        } else {
                            Veiculo g = this.veiculos.getVeiculoByMatricula(toAdd[0]);
                            this.proprietarios.addRatingToProp(g.getNif(),Double.parseDouble(toAdd[1]));
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
    private void login() throws DadosDeAcessoInvalidos, SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Utilizador(email): ");
        String user = scanner.nextLine();
        System.out.print("Password:  ");
        String pwd = scanner.nextLine();
//TODO
        if (this.clientes.getClienteByUser(user)!=null && this.clientes.getClienteByUser(user).validaPassword(pwd)) {
            cliente = this.clientes.getClienteByUser(user);
            menuCliente();
        } else if (this.proprietarios.getProprietariosByUser(user)!=null && this.proprietarios.getProprietariosByUser(user).validaPassword(pwd)) {
            proprietario = this.proprietarios.getProprietariosByUser(user);
            menuProprietario();
        } else throw new DadosDeAcessoInvalidos();

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
            System.out.println("Utilizador(email): ");
            String user = scanner.nextLine();
            System.out.println("Password:  ");
            String pwd = scanner.nextLine();
            System.out.println("Número de Idenficação Fiscal");
            Integer nif = scanner.nextInt();
            System.out.println("Nome: ");
            String nome = scanner.nextLine();
            System.out.println("Morada: ");
            String morada = scanner.nextLine();
            System.out.println("Data de Nascimento (mm/dia/ano): ");
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

    private void menuCliente() throws SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma das seguintes opcoes:" +
                "1: Classificar Proprietario/Veiculo das Última Viagem"+
                "2: Alugar Carro");
        String optionSelected = scanner.nextLine();
        switch (optionSelected) {
            case "1":
                Pedidos ps = this.pedidos.getPedidosPorClassificarDeCliente(this.cliente);
                if (ps != null){
                    System.out.println(ps);
                    System.out.println("Indique o id to pedido que quer realizar classificações:");
                    Integer id = scanner.nextInt();
                    System.out.println("Indique a classifcação a dar ao Proprietário:");
                    Double classProp = scanner.nextDouble();
                    System.out.println("Indique a classificação que quer dar ao Veículo:");
                    Double classVeic = scanner.nextDouble();
                    this.proprietarios.addRatingToProp(ps.selectPedidoById(id).getNifProprietario(), classProp);
                    this.veiculos.addRatingVeiculo(ps.selectPedidoById(id).getMatricula(), classVeic);
                    menuCliente();
            }
                break;
            case "2":
                menuAluguer();
                break;
            default : throw new OpcaoInvalida();
        }


    }

    private void menuAluguer() throws SemVeiculosDisponiveis {
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
            Pedido p = new Pedido(this.cliente.getNif(),v.getNif(), cordXDest, cordYDest, v.getMatricula(), preferencia, true,true);
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
        //Veiculos veiculosDoProp = this.veiculos.getVeiculosDeProprietário(this.proprietario.getNif());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione a sua opção\n" +
                "1: Abastecer Carro(alterar autonomia). \n" +
                "2: Alterar preço por km de Veículo. (km).\n" +
                "3: Adicionar Carro\n" +
                "4: Ver propostas de cliente.\n");
        String optionSelected = scanner.nextLine();
        switch(optionSelected){
            case "1":
                Veiculo v = this.veiculos.selectMatricula(this.veiculos);
                System.out.println("Indique a autonomia resultante do abastecimento do veículo:");
                Double autonomiaSelect = scanner.nextDouble();
                v.setAutonomia(autonomiaSelect);
                break;
            case "2":
                Veiculo v2 = this.veiculos.selectMatricula(this.veiculos);
                System.out.println("Indique o novo preço por km:");
                Double novoPrecoKm = scanner.nextDouble();
                v2.setPrecoPorKm(novoPrecoKm);
                break;
            case "3":
                addCarro();
                break;
            case "4":
                pedidosManager();
                break;
        }
    }



    private void pedidosManager() {
        Scanner scanner = new Scanner(System.in);
        Pedidos pedProp = this.pedidos.getPedidosDeProp(this.proprietario.getNif());
        System.out.println(pedProp);
        System.out.println("Introduza o id do pedido que quer gerir:");
        Integer idSelec = scanner.nextInt();
        Pedido p = this.pedidos.getPedidosDeProp(this.proprietario.getNif()).selectPedidoById(idSelec);
        System.out.println("Escolha uma das seguintes opções" +
                "1: Aceitar." +
                "2: Recusar."+
                "3: Re-selecionar pedido." +
                "4: Voltar ao menu anterior");
        String optionSelected = scanner.nextLine();
        switch (optionSelected) {
            case "1":
                p.setPendente(false);
                p.setPorClassificar(true);
                this.pedidos.addNewPedido(p);
                this.clientes.getClienteByNif(p.getNifCliente()).setRating(UserRatingManager(proprietario));
                break;
            case "2":
                this.pedidos.removePedido(p);
                break;
            case "3":
                pedidosManager();
                break;
            case "4":
                menuProprietario();
                break;
        }

    }

    private Double UserRatingManager(Utilizador x) {
        if (x instanceof Proprietario) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indique uma nota que deseja dar ao seu Cliente");
            Integer rating = scanner.nextInt();
            return Double.valueOf(rating);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Indique uma nota que queira dar ao Proprietario do seu veiculo");
            Integer rating2 = scanner.nextInt();
            return Double.valueOf(rating2);
        }
    }


    private void addCarro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduza o Tipo de Combustivel do Veículo (Gasolina/Electrico/Hibrido):");
        String tipo = scanner.nextLine();
        System.out.println("Introduza a marca do veículo");
        String marca = scanner.nextLine();
        System.out.println("Introduza a matrícula do veículo");
        String matricula = scanner.nextLine();
        //System.out.println("Introduza o seu NIF");
        //Integer nif = scanner.nextInt();
        System.out.println("Introduza a velocidade média do veículo (kmh):");
        Integer velocidademedia = scanner.nextInt();
        System.out.println("Introduza o preço por km: ");
        Double precoPorKm = scanner.nextDouble();
        System.out.println("Introduza o consumo por km: ");
        Double consumoporKm = scanner.nextDouble();
        System.out.println("Introduza a autonomia actual do veículo");
        Double autonomia = scanner.nextDouble();
        System.out.println("Introduza a localização X do veículo");
        Double cordX = scanner.nextDouble();
        System.out.println("Introduza a localização Y do veículo");
        Double cordY = scanner.nextDouble();
        Veiculo x = new Veiculo(tipo,marca,matricula,proprietario.getNif(),velocidademedia,precoPorKm,consumoporKm,autonomia,cordX,cordY,0.0);
        this.veiculos.addVeiculo(x);
        menuProprietario();
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


    public static void main(String[] args) throws DadosDeAcessoInvalidos, SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {

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

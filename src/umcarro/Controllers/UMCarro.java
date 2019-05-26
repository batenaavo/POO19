/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro.Controllers;




import umcarro.Controllers.Exceptions.NaoTemPedidos;
import umcarro.Controllers.Exceptions.OpcaoInvalida;
import umcarro.Controllers.Exceptions.SemVeiculosDisponiveis;
import umcarro.Models.*;
import umcarro.Views.View;

import java.io.*;
import java.util.Date;

public class UMCarro implements Serializable {

    private Cliente cliente;
    private Proprietario proprietario;
    private Clientes clientes;
    private Proprietarios proprietarios;
    private Veiculos veiculos;
    private Pedidos pedidos;
    private View view;
    private DataBaseSaver db = new DataBaseSaver();

    public UMCarro() {
        this.proprietarios = new Proprietarios();
        this.clientes = new Clientes();
        this.pedidos = new Pedidos();
        this.veiculos = new Veiculos();
        this.view = new View();
    }

    public void DataDump() throws IOException {
        try {
            FileReader fr = new FileReader("src/logsPOO_carregamentoInicial.bak");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("NovoProp:") || line.startsWith("NovoCliente:") || line.startsWith("NovoCarro:") || line.startsWith("Aluguer") || line.startsWith("Classificar:")) {
                    String[] aux = line.split(":");
                    String[] toAdd = aux[1].split(",");
                    switch (aux[0]) {
                        case "NovoProp":
                            Proprietario p = new Proprietario(toAdd[0], Integer.parseInt(toAdd[1]), toAdd[2], toAdd[3],
                                    new Date(1994, 12, 16), "pass");
                            this.proprietarios.addProprietario(p);
                            break;
                        case "NovoCliente":
                            Cliente c = new Cliente(toAdd[0], Integer.parseInt(toAdd[1]), toAdd[2], toAdd[3], new Date(1994,
                                    12, 16), "pass", Double.parseDouble(toAdd[4]), Double.parseDouble(toAdd[5]));
                            this.clientes.addCliente(c);
                            break;
                        case "NovoCarro":
                            Veiculo v = new Veiculo(toAdd[0], toAdd[1], toAdd[2], Integer.parseInt(toAdd[3]),
                                    Integer.parseInt(toAdd[4]), Double.parseDouble(toAdd[5]), Double.parseDouble(toAdd[6]),
                                    Double.parseDouble(toAdd[7]), Double.parseDouble(toAdd[8]), Double.parseDouble(toAdd[9]), 0.0);
                            this.veiculos.addVeiculo(v);
                            break;
                        case "Aluguer":
                            Integer nif = Integer.parseInt(toAdd[0]);
                            Pedido a = new Pedido(null, nif, Double.parseDouble(toAdd[1]),
                                    Double.parseDouble(toAdd[2]), null, toAdd[4], false, false,0.0);
                            String tipoComb = toAdd[3];
                            if (a.getPreferencia().equals("MaisBarato")) {
                                Veiculo v1 = veiculos.selectMaisBarato(a.getCordXDest(), a.getCordYDest(),
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
                            if (toAdd[0].length() > 8) {
                                this.clientes.addRating(Integer.parseInt(toAdd[0]), Double.parseDouble(toAdd[1]));
                                this.proprietarios.addRatingToProp(Integer.parseInt(toAdd[0]), Double.parseDouble(toAdd[1]));
                            } else {
                                Veiculo g = this.veiculos.getVeiculoByMatricula(toAdd[0]);
                                this.proprietarios.addRatingToProp(g.getNif(), Double.parseDouble(toAdd[1]));
                                /* mudar rating de proprietarios para carros.*/
                            }
                            break;
                    }
                } else br.readLine();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    void login() throws SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {
        this.view.printMensagem("Utilizador(email): ");
        String user = this.view.getString();
        this.view.printMensagem("Password:  ");
        String pwd = this.view.getString();
        if (this.clientes.getClienteByUser(user)!=null && this.clientes.getClienteByUser(user).validaPassword(pwd)) {
            cliente = this.clientes.getClienteByUser(user);
            menuCliente();
        } else if (this.proprietarios.getProprietariosByUser(user)!=null && this.proprietarios.getProprietariosByUser(user).validaPassword(pwd)) {
            proprietario = this.proprietarios.getProprietariosByUser(user);
            menuProprietario();
        } else System.out.println("Dados de acesso inválidos!");
        this.view.menuInicial();

    }

    public void registo(){
        String userType = this.view.userTypeSelect();
        if (!userType.equals("1") && !userType.equals("2")){
            this.view.printMensagem("Opção Inválida! ");
            registo();
        } else {
            this.view.printMensagem("Utilizador(email): ");
            String user = this.view.getString();
            this.view.printMensagem("Password:  ");
            String pwd = this.view.getString();
            Integer nif;
            this.view.printMensagem("Nome: ");
            String nome = this.view.getString();
            do {
                this.view.printMensagem("Número de Idenficação Fiscal; ");
                nif = this.view.getInteger();
                this.view.getString();
            } while (String.valueOf(nif).length() != 9);
            this.view.printMensagem("Morada: ");
            String morada = this.view.getString();
            this.view.printMensagem("Data de Nascimento (mm/dia/ano): ");
            String dataNasc = this.view.getString();
            if (userType.equals("1")) {
                this.view.printMensagem("Localização X");
                Double cordX = this.view.getDouble();
                this.view.printMensagem("Localização Y");
                Double cordY = this.view.getDouble();
                this.view.getString();
                Cliente c = new Cliente(nome,nif,user,morada,new Date(dataNasc),pwd, cordX, cordY);
                if (this.clientes.getClienteByUser(user)== null && this.clientes.getClienteByNif(nif) == null) {
                    this.clientes.addCliente(c);
                    this.view.printMensagem("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    this.view.printMensagem("Utilizador já existente! ");
                }
            } else {
                Proprietario p = new Proprietario(nome,nif,user,morada,new Date(dataNasc),pwd);
                if (this.proprietarios.getProprietariosByUser(user)== null && this.proprietarios.getProprietarioByNif(nif)==null) {
                    this.proprietarios.addProprietario(p);
                    this.view.printMensagem("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    this.view.printMensagem("Utilizador já existente! ");
                }
            }
        }
    }

    private void menuCliente() throws SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {

        String optionSelected = this.view.opcoesCliente();
        switch (optionSelected) {
            case "1":
                Pedidos ps = this.pedidos.getPedidosPorClassificarDeCliente(this.cliente);
                if (ps != null){
                    //PRINT DO OBJECTO PEDIDOS ???? WTF ???? TALVEZ ps.toString()
                    System.out.println(ps);
                    //??
                    this.view.printMensagem("Indique o id to pedido que quer realizar classificações:");
                    Integer id = this.view.getInteger();
                    this.view.printMensagem("Indique a classifcação a dar ao Proprietário:");
                    Double classProp = this.view.getDouble();
                    this.view.printMensagem("Indique a classificação que quer dar ao Veículo:");
                    Double classVeic = this.view.getDouble();
                    this.proprietarios.addRatingToProp(ps.selectPedidoById(id).getNifProprietario(), classProp);
                    this.veiculos.addRatingVeiculo(ps.selectPedidoById(id).getMatricula(), classVeic);
                    menuCliente();
            }
                break;
            case "2":
                menuAluguer();
                break;
            case "3":
                this.view.menuInicial();
                break;
            default : throw new OpcaoInvalida();
        }


    }

    private void menuAluguer() throws SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos {
        this.view.printMensagem("Indique a sua localização actual x:");
        Double locX = this.view.getDouble();
        this.view.printMensagem("Indique a sua localização actual y:");
        Double locY = this.view.getDouble();
        this.view.printMensagem("Indique a localização x para onde quer viajar:");
        Double tripX = this.view.getDouble();
        this.view.printMensagem("Indique a localização y para onde quer viajar:");
        Double tripY = this.view.getDouble();

        Integer combustívelPref = this.view.perferenciaCompostivel();
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

        Integer optionSelected = this.view.opcoesAluger();
        switch (optionSelected) {
            case 1:
                Veiculo veiculoMaisBarato = veiculos.selectMaisBarato(tripX, tripY, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisBarato", veiculoMaisBarato);
                break;
            case 2:
                Veiculo veiculoMaisProximo = veiculos.selectMaisProximo(locX, locY, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisPerto", veiculoMaisProximo);
                menuCliente();
                break;
            case 3:
                this.view.printMensagem("Indique a distância máxima à qual pretende encontrar uma viatura disponível");
                Double raio = this.view.getDouble();
                Veiculo vSelecionado = veiculos.selectMaisBaratoAlcancavel(raio,locX, locY,
                    tripX, tripY,
                        veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "MaisBaratoAlcançavel", vSelecionado);
                menuCliente();
                break;
            case 4:
                Veiculo vSelec1 = veiculos.selectVeiculoEspecifico(veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "VeiculoEspecifico", vSelec1);
                menuCliente();
                break;
            case 5:
                this.view.printMensagem("Indique a autonomia desejada:");
                Double autonomiaDes = this.view.getDouble();
                Veiculo vSelec2 = veiculos.selectVeiculoAutonomia(autonomiaDes, veiculosRelevantes);
                confirmationVehicle(this.cliente.getNif(), tripX, tripY, "VeiculoAutonomia", vSelec2);
                menuCliente();
                break;

        }
    }



    public boolean confirmationVehicle(Integer nif,  Double cordXDest, Double cordYDest,
                                           String preferencia, Veiculo v) throws SemVeiculosDisponiveis, NaoTemPedidos, OpcaoInvalida {
        System.out.print(v.toString());
        this.view.printMensagem("Confirma a sua seleção? (Y/N)?");
        this.view.getString();
        String resp = this.view.getString();
        if (resp.equals("Y") || resp.equals("y")) {
            Pedido p = new Pedido(this.cliente.getNif(),v.getNif(), cordXDest, cordYDest, v.getMatricula(),
                    preferencia, true,true,0.0);
            p.setPrecoViagem(getTravelDist(p));
            this.pedidos.addNewPedido(p);
            menuCliente();
            return true;
        } else if (resp.equals("N") || resp.equals("n")){
            menuCliente();
            return false;
        } else {
            this.view.printMensagem("Resposta Inválida");
            menuCliente();
            return false;
        }
    }


    private void menuProprietario() {
        //Veiculos veiculosDoProp = this.veiculos.getVeiculosDeProprietário(this.proprietario.getNif());

        String optionSelected = this.view.opcoesProprietario();
        switch(optionSelected){
            case "1":
                Veiculo v = this.veiculos.selectMatricula(this.veiculos);
                this.view.printMensagem("Indique a autonomia resultante do abastecimento do veículo:");
                Double autonomiaSelect = this.view.getDouble();
                v.setAutonomia(autonomiaSelect);
                break;
            case "2":
                Veiculo v2 = this.veiculos.selectMatricula(this.veiculos);
                this.view.printMensagem("Indique o novo preço por km:");
                Double novoPrecoKm = this.view.getDouble();
                v2.setPrecoPorKm(novoPrecoKm);
                break;
            case "3":
                addCarro();
                break;
            case "4":
                pedidosManager();
                break;
            case "5":
                this.view.menuInicial();


        }
    }

    private void pedidosManager() {
        Pedidos pedProp = this.pedidos.getPedidosDeProp(this.proprietario.getNif());
        if (pedProp != null) {
            //PRINT DO OBJECTO PEDIDOS ???? WTF ???? TALVEZ pedProp.toString()
            System.out.println(pedProp);
            //???
            this.view.printMensagem("Introduza o id do pedido que quer gerir:");
            Integer idSelec = this.view.getInteger();
            Pedido p = this.pedidos.getPedidosDeProp(this.proprietario.getNif()).selectPedidoById(idSelec);

            String optionSelected = this.view.opcoesPedidos();
            switch (optionSelected) {
                case "1":
                    p.setPendente(false);
                    p.setPorClassificar(true);
                    this.pedidos.addNewPedido(p);
                    this.clientes.getClienteByNif(p.getNifCliente()).setRating(UserRatingManager(proprietario));
                    Double traveldist = getTravelDist(p);
                    this.veiculos.getVeiculoByMatricula(p.getMatricula()).setAutonomia((this.veiculos.getVeiculoByMatricula(p.getMatricula()).getAutonomia()) - traveldist);
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

        } else this.view.printMensagem("Não tem pedidos a gerir!.");
                this.menuProprietario();
    }

    private void precoViagem(Pedido p, Double traveldist) {
        p.setPrecoViagem(this.veiculos.getVeiculoByMatricula(p.getMatricula()).getPrecoPorKm()*(traveldist));
    }

    private Double getTravelDist(Pedido p) {
        return this.veiculos.distanciaEntrePontos((this.veiculos.getVeiculoByMatricula(p.getMatricula()).getCordX()),(p.getCordXDest()),(this.veiculos.getVeiculoByMatricula(p.getMatricula()).getCordY()),p.getCordYDest());
    }

    private Double UserRatingManager(Utilizador x) {
        if (x instanceof Proprietario) {
            this.view.printMensagem("Indique uma nota que deseja dar ao seu Cliente");
            Integer rating = this.view.getInteger();
            return Double.valueOf(rating);
        } else {
            this.view.printMensagem("Indique uma nota que queira dar ao Proprietario do seu veiculo");
            Integer rating2 = this.view.getInteger();
            return Double.valueOf(rating2);
        }
    }


    private void addCarro() {
        this.view.printMensagem("Introduza o Tipo de Combustivel do Veículo (Gasolina/Electrico/Hibrido):");
        String tipo = this.view.getString();
        this.view.printMensagem("Introduza a marca do veículo");
        String marca = this.view.getString();
        this.view.printMensagem("Introduza a matrícula do veículo");
        String matricula = this.view.getString();
        //System.out.println("Introduza o seu NIF");
        //Integer nif = scanner.nextInt();
        this.view.printMensagem("Introduza a velocidade média do veículo (kmh):");
        Integer velocidademedia = this.view.getInteger();
        this.view.printMensagem("Introduza o preço por km: ");
        Double precoPorKm = this.view.getDouble();
        this.view.printMensagem("Introduza o consumo por km: ");
        Double consumoporKm = this.view.getDouble();
        this.view.printMensagem("Introduza a autonomia actual do veículo");
        Double autonomia = this.view.getDouble();
        this.view.printMensagem("Introduza a localização X do veículo");
        Double cordX = this.view.getDouble();
        this.view.printMensagem("Introduza a localização Y do veículo");
        Double cordY = this.view.getDouble();
        Veiculo x = new Veiculo(tipo,marca,matricula,proprietario.getNif(),velocidademedia,precoPorKm,consumoporKm,autonomia,cordX,cordY,0.0);
        this.veiculos.addVeiculo(x);
        menuProprietario();
    }


    public View getView() {
        return view;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Proprietarios getProprietarios() {
        return proprietarios;
    }

    public void setProprietarios(Proprietarios proprietarios) {
        this.proprietarios = proprietarios;
    }

    public Veiculos getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Veiculos veiculos) {
        this.veiculos = veiculos;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    public void setView(View view) {
        this.view = view;
    }

    public DataBaseSaver getDb() {
        return db;
    }

    public void setDb(DataBaseSaver db) {
        this.db = db;
    }
}

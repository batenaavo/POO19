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
    private HashMap<Integer,Cliente> clientes;
    private Proprietarios proprietarios;
    private Veiculos veiculos;

    public UMCarro() {
        this.clientes = new HashMap<>();
        this.proprietarios = new Proprietarios();
        this.cliente = null;
        this.proprietario = null;
    }

    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }

    public Proprietarios getProprietarios() {
        return proprietarios;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    private void DataDump(){
        try{
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null) {
                String[] aux = line.split(":");
                String[] toAdd = aux[1].split(",");
                switch (aux[0]){
                    case "NovoProp":
                        Proprietario p = new Proprietario(toAdd[0],Integer.parseInt(toAdd[1]),toAdd[2],toAdd[3],new Date(1994,12,16),"pass");
                        this.proprietarios.addProprietario(p);
                        break;
                    case "NovoCliente":
                        Cliente c = new Cliente(toAdd[0],Integer.parseInt(toAdd[1]),toAdd[2],toAdd[3],new Date(1994,12,16),"pass",Float.parseFloat(toAdd[4]),Float.parseFloat(toAdd[5]));
                        this.clientes.put(Integer.parseInt(toAdd[1]),c);
                        break;
                    case "NovoCarro":
                        Veiculo v = new Veiculo();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            // TODO: handle exception
        }




        Veiculo v = new Veiculo("diesel","Fiat","00-AA-00",123123123,60,0.50, 0.50, 800, 0f , 0f);
        p.setVeiculo(v.getMatricula(),v);
        this.veiculos = new Veiculos();
        this.veiculos.addVeiculo(v);
        this.clientes.put(c.getUsername(),c);
        this.proprietarios.put(p.getUsername(),p);
    }

    private void login(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Utilizador(email): ");
        String user = scanner.nextLine();
        System.out.print("Password:  ");
        String pwd = scanner.nextLine();

        if (this.clientes.get(user)!=null && this.clientes.get(user).validaPassword(pwd)) {
            cliente = this.clientes.get(user);
            this.menuCliente();
        } else if (this.proprietarios.get(user)!=null && this.proprietarios.get(user).validaPassword(pwd)) {
            proprietario = this.proprietarios.get(user);
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
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Morada: ");
            String morada = scanner.nextLine();
            System.out.print("Data de Nascimento (mm/dia/ano): ");
            String dataNasc = scanner.nextLine();

            if (userType.equals("1")) {
                System.out.print("Número Cartão de Crédito");
                Integer nCartaoCred = scanner.nextInt();
                System.out.print("Validade Cartão de Crédito (mm/yy): ");
                String validadeCartaoCred = scanner.nextLine();
                System.out.print("Código Segurança (CCV):  ");
                Integer codSeguranca = scanner.nextInt();
                Cliente c = new Cliente(user,nome,pwd,morada,new Date(dataNasc),nCartaoCred,validadeCartaoCred,codSeguranca,0f,0f);
                if (this.clientes.get(user)== null) {
                    this.clientes.put(user,c);
                    System.out.println("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    System.out.println("Utilizador já existente! ");
                }
            } else {
                Proprietario p = new Proprietario(user,nome,pwd,morada,new Date(dataNasc));
                if (this.proprietarios.get(user)== null) {
                    this.proprietarios.put(user,p);
                    System.out.println("Utilizador registado com sucesso, por favor proceda a login");
                } else {
                    System.out.println("Utilizador já existente! ");
                }
            }
        }
    }

    private void menuProprietario() {
        System.out.println("WTFFF");
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

    private void menuCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecione a sua opção\n" +
                "1: Ver Lista de Veiculos disponíveis. \n" +
                "2: Ver Veiculo mais próximo. \n" +
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

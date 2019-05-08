/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class UMCarro {

    private Cliente cliente;
    private Proprietario proprietario;
    private HashMap<String,Cliente> clientes;
    private HashMap<String,Proprietario> proprietarios;
    private Veiculos veiculos;

    public UMCarro() {
        this.clientes = new HashMap<>();
        this.proprietarios = new HashMap<>();
        this.cliente = null;
        this.proprietario = null;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public HashMap<String, Proprietario> getProprietarios() {
        return proprietarios;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    private void DataDump(){
        Cliente c = new Cliente ("Joao@email.com","joao","password150","Rua da Josefina",new Date(2000,10,10),22987,"07/10",123, 23.03f,10.02f);
        Proprietario p = new Proprietario("josefina@email.com","josefina","password123","Rua das Cabeçeiras", new Date(2000,9,10));

        Veiculo v = new Veiculo("00-AA-00", "Fiat", "LaPata", "disel", 10, 10, 330.04f,551.04f);
        p.setVeiculo(v.getMatricula(),v);

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
        System.out.println("Deseja solicitar o carro selecionado? (Y/N)?");
        String resp = scanner.nextLine();
        if (resp == "Y" || resp == "y") {
            System.out.println("Proposta Enviada, aguarde resposta.");
        } else {
            autonomiaSelector(listaVeiculos);
        }




        //TODO


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
                menuVeiculos();
                break;
            case "2":
                this.veiculos.veiculoMaisProximo(this.cliente.getCordX(),this.cliente.getCordY());
                break;
            case "3":



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

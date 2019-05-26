package umcarro.Controllers;

import umcarro.Controllers.Exceptions.DadosDeAcessoInvalidos;
import umcarro.Controllers.Exceptions.NaoTemPedidos;
import umcarro.Controllers.Exceptions.OpcaoInvalida;
import umcarro.Controllers.Exceptions.SemVeiculosDisponiveis;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws DadosDeAcessoInvalidos, SemVeiculosDisponiveis, OpcaoInvalida, NaoTemPedidos, FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UMCarro app = new UMCarro();
        DataBaseSaver db = new DataBaseSaver();


        if (db.loadData() == 0) {
            app.DataDump(); //Inicialização de Dados
        }


        boolean sair = false;

        while(!sair){
            String opcao = app.getView().menuInicial();
            switch (opcao) {
                case "1":
                    app.login();
                    break;
                case "2":
                    app.registo();
                    break;
                case "3":
                    sair = true;
                    app.getView().printMensagem("Obrigado!");

                    break;
                default:
                    app.getView().printMensagem("Opção não encontrada!");
                    db.saveData("estado",app);
                    break;
            }
        }
    }


}

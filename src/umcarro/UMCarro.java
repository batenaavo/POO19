/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umcarro;



import java.util.Date;
import java.util.HashMap;

public class UMCarro {

    public static void main(String[] args) {
        HashMap<String,Cliente> clientes = new HashMap<>();
        HashMap<String,Proprietario> proprietarios = new HashMap<>();

        Cliente c = new Cliente ("Joao@email.com","joao","password150","Rua da Josefina",new Date(2000,10,10),22987,"07/10",123, 23.03f,10.02f);
        Proprietario p = new Proprietario("josefina@email.com","josefina","password123","Rua das Cabeçeiras", new Date(2000,9,10));

        Veiculo v = new Veiculo("00-AA-00", "Fiat", "LaPata", "disel", 10, 10, 330.04f,551.04f);
        p.setVeiculo(v.getMatricula(),v);

        System.out.println(c.toString());
        System.out.println(p.toString());

    }
    
}

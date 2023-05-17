import model.ClienteExemplo;
import repository.ClienteDAOExemplo;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenu = {"Cadastros", "Processos", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Processos

                break;
            case 2: //Relatorios
                JOptionPane.showMessageDialog(null, ClienteDAOExemplo.buscaTodos());
                chamaMenuPrincipal();
                break;
            case 3: //SAIR

                break;
        }
    }

    private static void chamaMenuCadastros() {
        String[] opcoesMenuCadastro = {"Cliente", "Produto", "Venda", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastros",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Pessoa
                cadastroDeCliente();
                break;
            case 1: //Seguradoras
                break;
            case 2: //Seguro
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroDeCliente() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");
        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente");
        String email = JOptionPane.showInputDialog(null, "Digite o email do cliente");
        ClienteExemplo cliente = new ClienteExemplo(nome, cpf, email);
        ClienteDAOExemplo.salvar(cliente);
        chamaMenuPrincipal();
    }

    private static void cadastroCompra(){
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o nome do cliente"));
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");
    }

}
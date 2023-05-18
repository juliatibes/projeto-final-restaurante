import model.ClienteExemplo;
import model.Compra;
import model.UnidadeMedidaEnum;
import repository.ClienteDAOExemplo;
import repository.CompraDAO;

import javax.swing.*;
import java.math.BigDecimal;

public class Main {


    public static void main(String[] args) {
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenu = {"Cadastros", "Compras", "Relatorios", "Sair"};
        int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);
        switch (opcao) {
            case 0: //Cadastros
                chamaMenuCadastros();
                break;
            case 1: //Compras
                cadastroCompra();
                break;
            case 2: //Relatorios
                JOptionPane.showMessageDialog(null, CompraDAO.listarCompras());
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
        UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.GRAMA,UnidadeMedidaEnum.UNIDADE,UnidadeMedidaEnum.LITRO,
                UnidadeMedidaEnum.KILOGRAMA,UnidadeMedidaEnum.MILIGRAMA,UnidadeMedidaEnum.MILILITRO};
        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do item"));
        String dataCompra = JOptionPane.showInputDialog(null, "Digite a data do item");
        String dataValidade = JOptionPane.showInputDialog(null, "Digite o data de validade do item");
        String nomeProduto = JOptionPane.showInputDialog(null, "Digite o nome do item");
        Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a quantidade"));
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha a unidade de medida:","Cadastro de produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
        BigDecimal valorCompra = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor:")));
        Compra produto = new Compra(id,dataCompra,dataValidade,nomeProduto,quantidade,opcoesUnidadeMedida[menuCadastro],valorCompra);
        CompraDAO.AdicionarNovaCompra(produto);
        chamaMenuPrincipal();

    }

}
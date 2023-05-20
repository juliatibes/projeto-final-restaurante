import model.ClienteExemplo;
import model.Compra;
import model.Produto;
import model.UnidadeMedidaEnum;
import repository.ClienteDAOExemplo;
import repository.CompraDAO;
import repository.ProdutoDAO;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenuPrincipal = {"Planejamento de Produção","Estoque","Compras", "Receitas", "Cardapio", "Venda","Relatorios","Sair"};
        int opcaoMenuPrincipal = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPrincipal, opcoesMenuPrincipal[0]);
        switch (opcaoMenuPrincipal) {
            case 0: //Planejamento de produção
                chamaMenuPlanejamento();
                break;
            case 1: //Estoque
                chamaMenuEstoque();
                break;
            case 2: //Compra
                //chamaMenuCadastroCompra();
                break;
            case 3:// Receitas
               // chamaMenuCadastroReceitas();
                break;
            case 4:// Cardapio
               // chamaMenuCardapio();
                break;
            case 5:// Vendas
               // chamaMenuVenda();
                break;
            case 6:// Relatorios
                //chamaMenuRelatorios();
                JOptionPane.showMessageDialog(null, CompraDAO.listarCompras());
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

    private static void chamaMenuPlanejamento() {
        String[] opcoesMenuPlanejamento = {"Planejado", "Produzido","Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Planejado
               // chamaCadastroPlanejado();
                break;
            case 1: //Produzido
               // chamaCadastroProduzido();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }
    private static void chamaMenuEstoque() {
        String[] opcoesMenuCadastro = {"Produto", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Estoque",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //Produto
                cadastroProduto();
                break;
            case 1: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroProduto() {

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do item"));
        String nomeProduto = JOptionPane.showInputDialog(null, "Digite o nome do item");
        BigDecimal valorUnitario = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o valor:")));
        Produto produto1 = new Produto(id, nomeProduto, valorUnitario);


        String[] opcoesMenuCadastroProduto = {"Novo Cadastro", "Cancelar","Finalizar Cadastro"};
        int menuCadastroProduto = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroProduto, opcoesMenuCadastroProduto[0]);

        switch (menuCadastroProduto) {
            case 0: //NovoCadastro
                ProdutoDAO.salvarListaProdutos(produto1);
                cadastroProduto();
                break;
            case 1: //Cancelar
                chamaMenuPrincipal();
                break;
            case 2: //Finalizar Cadastro
                ProdutoDAO.salvarListaProdutos(produto1);
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroCompra() {

        UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.UNIDADE, UnidadeMedidaEnum.LITRO,
                UnidadeMedidaEnum.KILOGRAMA, UnidadeMedidaEnum.MILIGRAMA, UnidadeMedidaEnum.MILILITRO};

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do item"));

        LocalDate dataCompra = LocalDate.now();
        String inputData = JOptionPane.showInputDialog(null, "Digite uma data (formato: dd/MM/yyyy):");
        try {
            dataCompra = LocalDate.parse(inputData, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido!");
        }

        Object[] selectionValuesProdutos = ProdutoDAO.findprodutosInArray();
        String initialSelectionProduto = (String) selectionValuesProdutos[0];
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto da venda",
                "VendasApp", JOptionPane.QUESTION_MESSAGE, null, selectionValuesProdutos, initialSelectionProduto);
        List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);

        Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a quantidade"));
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha a unidade de medida:", "Cadastro de produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
        Compra compra = new Compra(id, dataCompra, produtos.get(0), quantidade, opcoesUnidadeMedida[menuCadastro]);
        CompraDAO.salvarNovaCompra(compra);
        chamaMenuPrincipal();


    }
}
import model.*;
import repository.*;
import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static repository.PlanejamentoProducaoDAO.planeja;


public class Main {


    public static void main(String[] args) {
        ProdutoDAO.inputProdutos();
        CompraDAO.inputCompras();
        ReceitaDAO.inputReceita();
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenuPrincipal = {"Planejamento de Produção", "Estoque", "Compra", "Receita", "Cardapio", "Venda", "Relatorio", "Sair"};
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
                chamaMenuCadastroCompra();
                break;
            case 3:// Receitas
                chamaMenuCadastroReceitas();
                break;
            case 4:// Cardapio
                // chamaMenuCardapio();
                break;
            case 5:// Vendas
                // chamaMenuVenda();
                break;
            case 6:// Relatorios
                chamaMenuRelatorios();
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
        String[] opcoesMenuPlanejamento = {"Cadastrar planejamento", "Planejamento", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Cadastrar Planejamento
                //cadastroPlanejamento();
                break;
            case 1: //Planejado
               // planeja()
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaMenuEstoque() {
        String[] opcoesMenuCadastro = {"Cadastrar Produto", "Remover Produto", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Estoque",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastrarProduto
                cadastroProduto();
                break;
            case 1: //RemoverProduto
                removerProduto();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroProduto() {

        ProdutoEnum[] opcoesTipoPagamento = {ProdutoEnum.BEBIBA, ProdutoEnum.INGREDIENTE};

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o id do item",
                "Cadastro Produto", JOptionPane.DEFAULT_OPTION));
        String nomeProduto = JOptionPane.showInputDialog(null, "Digite o nome do item",
                "Cadastro Produto", JOptionPane.DEFAULT_OPTION);
        int ProdutoSelecionado = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto:", "Cadastro Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
        Produto produto1 = new Produto(id, nomeProduto, opcoesTipoPagamento[ProdutoSelecionado]);


        String[] opcoesMenuCadastroProduto = {"Novo Cadastro", "Cancelar", "Finalizar Cadastro"};
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

    private static void removerProduto() {

        Object[] selectionValuesProdutos = ProdutoDAO.findprodutosInArray();
        String initialSelectionProduto = (String) selectionValuesProdutos[0];
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto para remover:",
                "Remover Produto", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutos, initialSelectionProduto);
        List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);
        ProdutoDAO.removerProduto(produtos.get(0));
        chamaMenuEstoque();
    }

    private static void chamaMenuCadastroCompra() {
        String[] opcoesMenuCadastroCompra = {"Cadastrar Compra","Remover Compra","Editar Compra","Voltar"};
        int menuCadastroCompra = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar Compras",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroCompra, opcoesMenuCadastroCompra[0]);

        switch (menuCadastroCompra) {
            case 0: //Adicionar Compra
                cadastroCompra();
                break;
            case 1: //Remover Compra

                break;
            case 2: //Editar Compra

                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }


    private static void cadastroCompra() {

       try{ UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.KILO, UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.LITRO,
                UnidadeMedidaEnum.MILILITRO, UnidadeMedidaEnum.UNIDADE};

        Integer id = CompraDAO.aiID();

        LocalDate dataCompra = LocalDate.now();
        String inputData = JOptionPane.showInputDialog(null, "Digite uma data (formato: dd/MM/yyyy):",
                "Cadastrar Compra", JOptionPane.DEFAULT_OPTION);
        try {
            dataCompra = LocalDate.parse(inputData, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido!",
                    "Cadastrar Compra", JOptionPane.ERROR_MESSAGE);
            cadastroCompra();
        }

        Object[] selectionValuesProdutos = ProdutoDAO.findprodutosInArray();
        String initialSelectionProduto = (String) selectionValuesProdutos[0];
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto da venda",
                "Cadastrar Compra", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutos, initialSelectionProduto);
        List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);

        Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a quantidade",
                "Cadastrar Compra", JOptionPane.DEFAULT_OPTION));
        int tipoUnidadeSelecionado = JOptionPane.showOptionDialog(null, "Informe a unidade de medida:",
                "Cadastrar Compra",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
        Compra compra = new Compra(id, dataCompra, produtos.get(0), quantidade, opcoesUnidadeMedida[tipoUnidadeSelecionado]);
        CompraDAO.salvarNovaCompra(compra);
        chamaMenuPrincipal();}
       catch (NullPointerException nullPointerException){
           chamaMenuPrincipal(); // tratamento de erro se clica em cancelar ( não sei se esta certo mas funcionou XD)
       }
    }

    public static void chamaMenuCadastroReceitas() {
        String[] opcoesMenuCadastro = {"Cadastrar Receita", "Remover Receita", "Editar Receita", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastrarReceita
                cadastroReceita();
                break;
            case 1: //RemoverReceita
                removerReceita();
                break;
            case 2: //EditarReceita
                //editarReceita();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    public static void cadastroReceita() {

        Integer x = 0;

        UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.KILO, UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.LITRO,
                UnidadeMedidaEnum.MILILITRO, UnidadeMedidaEnum.UNIDADE};
        ReceitaClasseEnum[] opcoesReceitaClasse = {ReceitaClasseEnum.ENTRADA,ReceitaClasseEnum.MASSA,ReceitaClasseEnum.RISOTO,
                ReceitaClasseEnum.CARNE,ReceitaClasseEnum.SOBREMESA};

        Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o ID:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION));
        String nome = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION);
        int receitaClasseSelecionado = JOptionPane.showOptionDialog(null, "Escolha a classe da receita:",
                "Cadastrar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesReceitaClasse, opcoesReceitaClasse[0]);

        Receita receita1 = new Receita(id, nome,opcoesReceitaClasse[receitaClasseSelecionado]);

        do {
            Object[] selectionValuesProdutos = ProdutoDAO.findprodutosInArrayIngrediente();
            String initialSelectionProduto = (String) selectionValuesProdutos[0];
            Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o ingrediente da Receita",
                    "Cadastrar Receita", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutos, initialSelectionProduto);
            List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);

            Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe a quantidade",
                    "Cadastrar Receita", JOptionPane.DEFAULT_OPTION));

            int tipoUnidadeSelecionado = JOptionPane.showOptionDialog(null, "Informe a unidade de medida:",
                    "Cadastrar Receita",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);

            ReceitaIngrediente ingrediente = new ReceitaIngrediente(produtos.get(0), quantidade, opcoesUnidadeMedida[tipoUnidadeSelecionado]);
            receita1.adicionarIngrediente(ingrediente);

            String[] opcoesMenuCadastroReceita = {"Adicionar Novo Ingrediente", "Remover ingrediente", "Finalizar Cadastro Ingrediente"};
            int menuCadastroReceita = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Cadastrar Receita Ingredientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroReceita, opcoesMenuCadastroReceita[0]);

            switch (menuCadastroReceita) {
                case 0: //Adicionar Novo Ingrediente

                    break;
                case 1: //RemoverIngrediente
                    x = 3;
                    break;
                case 2: //Finaliza Cadastro
                    x = 2;
                    break;
            }
        }
        while (x <= 0);

        String[] opcoesMenuCadastroReceita = {"Novo Cadastro Receita", "Cancelar", "Finalizar Cadastro Receita"};
        int menuCadastroReceita = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroReceita, opcoesMenuCadastroReceita[0]);

        switch (menuCadastroReceita) {
            case 0: //NovoCadastro
                ReceitaDAO.salvarNovaReceita(receita1);
                cadastroReceita();
                break;
            case 1: //Cancelar
                chamaMenuPrincipal();
                break;
            case 2: //Finalizar Cadastro
                ReceitaDAO.salvarNovaReceita(receita1);
                chamaMenuPrincipal();
                break;


        }
    }
    private static void removerReceita() {

        Object[] selectionValuesReceita = ReceitaDAO.findreceitasInArray();
        String initialSelectionReceita = (String) selectionValuesReceita[0];
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto para remover:",
                "Remover Produto", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        List<Receita> receitas = ReceitaDAO.buscarPorNome((String) selectionProduto);
        ReceitaDAO.removerReceita(receitas.get(0));
        chamaMenuCadastroReceitas();
    }

    public static void chamaMenuRelatorios (){
        String[] opcoesMenuRelatorio = {"Compras","Vendas","ProdutoTeste","ReceitaTeste"};
        int menuRelatorios = JOptionPane.showOptionDialog(null,"Escolha uma opção:",
                "Relatorios",
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,opcoesMenuRelatorio,opcoesMenuRelatorio[0]);

        switch (menuRelatorios){

            case 0: //Compras
                JOptionPane.showMessageDialog(null,CompraDAO.buscarTodos());
                chamaMenuPrincipal();
                break;
            case 1: //Vendas
                chamaMenuPrincipal();
                break;
            case 2: //ProdutoTeste
                JOptionPane.showMessageDialog(null,ProdutoDAO.buscaTodos());
                chamaMenuPrincipal();
                break;
            case 3: //ReceitaTeste
                JOptionPane.showMessageDialog(null,ReceitaDAO.buscaTodos());
                chamaMenuPrincipal();
                break;
        }
    }





}
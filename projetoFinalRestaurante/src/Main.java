import model.*;
import repository.*;


import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) {
        ProdutoDAO.inputProdutos();
        CompraDAO.inputCompras();
        ReceitaDAO.inputReceita();
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
        String[] opcoesMenuPrincipal = {"Planejamento de Produção", "Estoque", "Compra", "Receita", "Venda", "Relatórios", "Sair"};
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
            case 4:// Vendas
                chamaMenuVenda();
                break;
            case 5:// Relatorios
                chamaMenuRelatorios();
                break;
        }
    }

    private static void chamaMenuPlanejamento() {
        String[] opcoesMenuPlanejamento = {"Cadastrar planejamento", "Oferta do Dia", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Planejamento de Produção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Cadastrar Planejamento
                cadastroPlanejamento();
                break;
            case 1: //menuOfertadoDia
                menuOfertaDia();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroPlanejamento() {

        String[] opcoesMenuPlanejamento = {"Receita", "Bebida", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastro Planejamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Cadastrar Planejamento receita
                cadastroPlanejamentoReceita();
                break;
            case 1: //Cadastrar Planejamento bebida
                cadastroPlanejamentoBebida();
                break;
            case 2: //Voltar
                chamaMenuPlanejamento();
                break;
        }

    }

    private static void cadastroPlanejamentoReceita() {

        if (ReceitaOfertaDoDIaDAO.findreceitasInArrayReceitaOferta().length < 1){
            JOptionPane.showConfirmDialog(null, "Não existe oferta do dia cadastrado !!!",
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuPrincipal();
        }

        Object[] selectionValuesReceita = ReceitaOfertaDoDIaDAO.findreceitasInArrayReceitaOferta();
        String initialSelectionReceita = (String) selectionValuesReceita[0];
        Object selectionReceitaEntrada = JOptionPane.showInputDialog(null, "Selecione a oferta do dia:",
                "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        List<ReceitaOfertaDia> receitaOfertaPlanejada = ReceitaOfertaDoDIaDAO.buscarPorNomeReceitaOferta((String) selectionReceitaEntrada);

        Integer quantidadePlanejada = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade desejada planejada:",
                "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION));

        Object[] listaIngredientesFaltantes = EstoqueDAO.verificaEstoqueReceitaPlanejamento(receitaOfertaPlanejada.get(0).getReceita(), quantidadePlanejada);

        if (listaIngredientesFaltantes.length > 2) {
            JOptionPane.showConfirmDialog(null, "Estoque Insuficiente!",
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            JOptionPane.showConfirmDialog(null, listaIngredientesFaltantes,
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuPrincipal();
        }else{

            PlanejamentoProducao planejamentoProducaoReceita = new PlanejamentoProducao
                    (receitaOfertaPlanejada.get(0).getReceita(), quantidadePlanejada);

            PlanejamentoProducaoDAO.salvarPlanejamento(planejamentoProducaoReceita);

            JOptionPane.showConfirmDialog(null, "Disponivel em estoque!\nPlanejamento cadastrado com sucesso!"
                    ,
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuPrincipal();
        }
    }



    private static void cadastroPlanejamentoBebida() {
        if (BebidaOfertaDoDIaDAO.findreceitasInArrayBebidaOferta().length < 1){
            JOptionPane.showConfirmDialog(null, "Não existe oferta do dia cadastrado !!!",
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuPrincipal();
        }
        Object[] selectionValuesBebida = BebidaOfertaDoDIaDAO.findreceitasInArrayBebidaOferta();
        String initialSelectionReceita = (String) selectionValuesBebida[0];
        Object selectionBebida = JOptionPane.showInputDialog(null, "Selecione a oferta do dia:",
                "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, null, selectionValuesBebida, initialSelectionReceita);
        List<BebidaOfertaDia> bebidaOfertaPlanejada = BebidaOfertaDoDIaDAO.buscarPorNomeBebidaOferta((String) selectionBebida);

        Integer quantidadePlanejada = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade desejada planejada:",
                "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION));

        Object[] listaBebidasFaltantes = EstoqueDAO.verificaEstoqueBebidaPlanejamento(bebidaOfertaPlanejada.get(0).getProduto(), quantidadePlanejada);

        if (listaBebidasFaltantes.length > 2) {
            JOptionPane.showConfirmDialog(null, "Estoque Insuficiente!",
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            JOptionPane.showConfirmDialog(null, listaBebidasFaltantes,
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuPrincipal();
        }else{

            PlanejamentoProducao planejamentoProducaoBebida =  new PlanejamentoProducao
                    (bebidaOfertaPlanejada.get(0).getProduto(), quantidadePlanejada);

            PlanejamentoProducaoDAO.salvarPlanejamento(planejamentoProducaoBebida);

            JOptionPane.showConfirmDialog(null, "Disponivel em estoque!\nPlanejamento cadastrado com sucesso!",
                    "Cadastro Planejamento", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuPrincipal();
        }
    }


    private static void menuOfertaDia (){
        String[] opcoesMenuOferta = {"Cadastrar Oferta", "Remover Oferta", "Voltar"};
        int menuOfertaDoDia = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Oferta do Dia",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuOferta, opcoesMenuOferta[0]);

        switch (menuOfertaDoDia) {
            case 0: //Cadastrar Oferta
                menuCadastroOferta();
                break;
            case 1: //RemoverOferta
                menuRemoverOfertaDia();
                break;
            case 2: //Voltar
                chamaMenuPlanejamento();
                break;
        }

    }

    private static void menuCadastroOferta(){

        String[] opcoesMenuPlanejamento = {"Receita", "Bebida", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Oferta do Dia",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Cadastrar Oferta receita
                menuCadastroOfertaReceita();
                break;
            case 1: //Cadastrar Oferta bebida
                menuCadastroOfertaBebida();
                break;
            case 2: //Voltar
                menuOfertaDia();
                break;
        }

    }

    private static void menuCadastroOfertaReceita(){

        Object[] selectionValuesReceita = ReceitaDAO.findreceitasInArray();
        String initialSelectionReceita = (String) selectionValuesReceita[0];
        Object selectionOfertaReceita = JOptionPane.showInputDialog(null, "Selecione uma receita para oferta do dia:",
                "Cadastro oferta do dia", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        List<Receita> receitas = ReceitaDAO.buscarPorNome((String) selectionOfertaReceita);
        Double desconto = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o desconto:",
                "Cadastro oferta do dia", JOptionPane.DEFAULT_OPTION));

        ReceitaOfertaDia receitaOfertaDia = new ReceitaOfertaDia(desconto,receitas.get(0));
        ReceitaOfertaDoDIaDAO.salvarOfertaReceita(receitaOfertaDia);

        chamaMenuPrincipal();

    }

    private static void menuCadastroOfertaBebida(){
        Object[] selectionValuesProdutoBebida = VendaDAO.findprodutosInArrayProdutoBebida();
        String initialSelectionProdutoBebida= (String) selectionValuesProdutoBebida[0];
        Object selectionOfertaProdutoBebida = JOptionPane.showInputDialog(null, "Selecione uma bebida para oferta do dia:",
                "Cadastro oferta do dia", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutoBebida, initialSelectionProdutoBebida);
        List<Produto> produtoBebida = ProdutoDAO.buscarPorNome((String) selectionOfertaProdutoBebida);
        Double desconto = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o desconto:",
                "Cadastro oferta do dia", JOptionPane.DEFAULT_OPTION));

        BebidaOfertaDia bebidaOfertaDia = new BebidaOfertaDia(desconto,produtoBebida.get(0));
        BebidaOfertaDoDIaDAO.salvarOfertaBebida(bebidaOfertaDia);
        chamaMenuPrincipal();
    }

    private static void menuRemoverOfertaDia(){

        String[] opcoesMenuPlanejamento = {"Receita", "Bebida", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Remover Oferta do Dia",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Remover Oferta receita
                menuRemoverOfertaDiaReceita();
                break;
            case 1: //Remover Oferta bebida
                menuRemoverOfertaDiaBebida();
                break;
            case 2: //Voltar
                menuOfertaDia();
                break;
        }

    }

    private static void menuRemoverOfertaDiaReceita(){
        if (ReceitaOfertaDoDIaDAO.findreceitasInArrayReceitaOferta().length < 1){
            JOptionPane.showConfirmDialog(null, "Não existe oferta do dia cadastrado !!!",
                    "Remover oferta do dia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuPrincipal();
        }

        Object[] selectionValuesOfertaDiaReceita = ReceitaOfertaDoDIaDAO.findreceitasInArrayReceitaOferta();
        String initialSelectionOfertaDiaReceita= (String) selectionValuesOfertaDiaReceita[0];
        Object selectionOfertaDiaReceita = JOptionPane.showInputDialog(null,
                "Selecione uma receita para remover da oferta do dia:", "Remover oferta do dia", JOptionPane.DEFAULT_OPTION,
                null, selectionValuesOfertaDiaReceita, initialSelectionOfertaDiaReceita);
        List<ReceitaOfertaDia> ofertaDiaReceita = ReceitaOfertaDoDIaDAO.buscarPorNomeReceitaOferta((String) selectionOfertaDiaReceita);

        ReceitaOfertaDoDIaDAO.removerOfertaReceita(ofertaDiaReceita.get(0));

        chamaMenuPrincipal();
    }

    private static void menuRemoverOfertaDiaBebida(){
        if (BebidaOfertaDoDIaDAO.findreceitasInArrayBebidaOferta().length < 1){
            JOptionPane.showConfirmDialog(null, "Não existe oferta do dia cadastrado !!!",
                    "Remover oferta do dia", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuPrincipal();
        }

        Object[] selectionValuesOfertaDiaBebida = BebidaOfertaDoDIaDAO.findreceitasInArrayBebidaOferta();
        String initialSelectionOfertaDiaBebida= (String) selectionValuesOfertaDiaBebida[0];
        Object selectionOfertaDiaBebida = JOptionPane.showInputDialog(null,
                "Selecione uma bebida para remover da oferta do dia:", "Remover oferta do dia", JOptionPane.DEFAULT_OPTION,
                null, selectionValuesOfertaDiaBebida, initialSelectionOfertaDiaBebida);
        List<BebidaOfertaDia> ofertaDiaBebida = BebidaOfertaDoDIaDAO.buscarPorNomeBebidaOferta((String) selectionOfertaDiaBebida);

        BebidaOfertaDoDIaDAO.removerOfertaBebida(ofertaDiaBebida.get(0));

        chamaMenuPrincipal();

    }

    private static void chamaMenuEstoque() {
        String[] opcoesMenuCadastro = {"Cadastrar Produto", "Remover Produto", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Estoque",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastrarProduto
                menuCadastroProduto();///
                break;
            case 1: //RemoverProduto
                removerProduto();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void menuCadastroProduto() {
        String[] opcoesMenuCadastro = {"Bebida", "Ingrediente", "Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto que deseja cadastrar:",
                "Cadastro Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastrarProdutoBebida
                cadastroProdutoBebida();
                break;
            case 1: //RemoverProdutoIngrediente
                cadastroProdutoIngrediente();
                break;
            case 2: //Voltar
                chamaMenuEstoque();
                break;
        }

    }

    private static void cadastroProdutoIngrediente() {



        ProdutoEnum[] opcoesTipoPagamento = {ProdutoEnum.BEBIDA, ProdutoEnum.INGREDIENTE};

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastro Produto Ingrediente", JOptionPane.DEFAULT_OPTION);

        Produto produto1 = new Produto(nomeProduto, opcoesTipoPagamento[1]);


        String[] opcoesMenuCadastroProduto = {"Novo Cadastro", "Cancelar", "Finalizar Cadastro"};
        int menuCadastroProduto = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroProduto, opcoesMenuCadastroProduto[0]);

        switch (menuCadastroProduto) {
            case 0: //NovoCadastro
                ProdutoDAO.salvarListaProdutos(produto1);
                menuCadastroProduto();
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

    private static void cadastroProdutoBebida() {

        ProdutoEnum[] opcoesTipoProduto = {ProdutoEnum.BEBIDA, ProdutoEnum.INGREDIENTE};


        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastro Produto Bebida", JOptionPane.DEFAULT_OPTION);

        BigDecimal valorCustoBebida = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Infome o valor de custo:",
                "Cadastrar Produto Bebida", JOptionPane.DEFAULT_OPTION)));

        Produto produto1 = new Produto(nomeProduto,opcoesTipoProduto[0],valorCustoBebida);


        String[] opcoesMenuCadastroProduto = {"Novo Cadastro", "Cancelar", "Finalizar Cadastro"};
        int menuCadastroProduto = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar Produto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroProduto, opcoesMenuCadastroProduto[0]);

        switch (menuCadastroProduto) {
            case 0: //NovoCadastro
                ProdutoDAO.salvarListaProdutos(produto1);
                menuCadastroProduto();
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
        chamaMenuPrincipal();
    }

    private static void chamaMenuCadastroCompra() {
        String[] opcoesMenuCadastroCompra = {"Cadastrar Compra", "Remover Compra", "Editar Compra", "Voltar"};
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

        try {
            UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.KILO, UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.LITRO,
                    UnidadeMedidaEnum.MILILITRO, UnidadeMedidaEnum.UNIDADE};



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
            Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto da compra",
                    "Cadastrar Compra", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutos, initialSelectionProduto);
            List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);

            Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a quantidade",
                    "Cadastrar Compra", JOptionPane.DEFAULT_OPTION));
            int tipoUnidadeSelecionado = JOptionPane.showOptionDialog(null, "Informe a unidade de medida:",
                    "Cadastrar Compra",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
            Compra compra = new Compra(dataCompra, produtos.get(0), quantidade, opcoesUnidadeMedida[tipoUnidadeSelecionado]);
            CompraDAO.salvarNovaCompra(compra);
            chamaMenuPrincipal();
        } catch (NullPointerException nullPointerException) {
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
                editarReceita();
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
        ReceitaClasseEnum[] opcoesReceitaClasse = {ReceitaClasseEnum.ENTRADA, ReceitaClasseEnum.MASSA, ReceitaClasseEnum.RISOTO,
                ReceitaClasseEnum.CARNE, ReceitaClasseEnum.SOBREMESA};


        String nome = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION);
        int receitaClasseSelecionado = JOptionPane.showOptionDialog(null, "Escolha a classe da receita:",
                "Cadastrar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesReceitaClasse, opcoesReceitaClasse[0]);
        BigDecimal valorCusto = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Infome o valor de custo:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION)));

        Receita receita1 = new Receita(nome, opcoesReceitaClasse[receitaClasseSelecionado], valorCusto);

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

            String[] opcoesMenuCadastroReceita = {"Adicionar Novo Ingrediente", "Finalizar Cadastro Ingrediente"};
            int menuCadastroReceita = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Cadastrar Receita Ingredientes",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastroReceita, opcoesMenuCadastroReceita[0]);

            switch (menuCadastroReceita) {
                case 0: //Adicionar Novo Ingrediente

                    break;
                case 1: //Finaliza Cadastro Ingrediente
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
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione a receita para remover:",
                "Remover Receita", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        Integer receitas = ReceitaDAO.buscaPosicaoReceita((String) selectionProduto);
        ReceitaDAO.removerReceita(receitas);
        chamaMenuCadastroReceitas();
    }

    private static void editarReceita() {

        ReceitaClasseEnum[] opcoesReceitaClasse = {ReceitaClasseEnum.ENTRADA, ReceitaClasseEnum.MASSA, ReceitaClasseEnum.RISOTO,
                ReceitaClasseEnum.CARNE, ReceitaClasseEnum.SOBREMESA};

        Object[] selectionValuesReceita = ReceitaDAO.findreceitasInArray();
        String initialSelectionReceita = (String) selectionValuesReceita[0];
        Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione a receita para editar:",
                "Editar Receita", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        Integer receitas = ReceitaDAO.buscaPosicaoReceita((String) selectionProduto);

        String[] opcoesEditarReceita = {"Nome", "Classe", "Valor de custo", "Ingredientes"};
        int menuEditarReceita = JOptionPane.showOptionDialog(null, "Escolha a opção que você deseja editar do(a) " +
                        ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesEditarReceita, opcoesEditarReceita[0]);

        switch (menuEditarReceita) {
            case 0: //editarReceitaNome
                String nome = JOptionPane.showInputDialog(null, "Informe o NOVO NOME para substituir "
                        + ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita", JOptionPane.DEFAULT_OPTION);
                ReceitaDAO.editarReceitaNome(receitas, nome);
                chamaMenuPrincipal();
                break;
            case 1: //editarReceitaClasse
                int receitaClasseSelecionado = JOptionPane.showOptionDialog(null, "Escolha a NOVA CLASSE do(a) " +
                                ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesReceitaClasse, opcoesReceitaClasse[0]);
                ReceitaDAO.editarReceitaClasse(receitas, opcoesReceitaClasse[receitaClasseSelecionado]);
                chamaMenuPrincipal();
            case 2: //editarReceitaValorCusto
                BigDecimal valorCusto = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null,
                        "Informe o NOVO VALOR DE CUSTO do(a) " + ReceitaDAO.buscaTodos().get(receitas).getNome() + ":",
                        "Editar Receita", JOptionPane.DEFAULT_OPTION)));
                ReceitaDAO.editarReceitaValorCusto(receitas, valorCusto);
                chamaMenuPrincipal();
                break;

            case 3: // editarReceitaIngredientes
                Integer contadorWhile = 0;

                UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.KILO, UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.LITRO,
                        UnidadeMedidaEnum.MILILITRO, UnidadeMedidaEnum.UNIDADE};

                Object[] selectionValuesReceitaIngrediente = ReceitaDAO.findReceitasIngredientesInArray(((String) selectionProduto));
                String initialSelectionReceitaIngrediente = (String) selectionValuesReceita[0];
                Object selectionReceitaIngrediente = JOptionPane.showInputDialog(null, "Selecione o ingrediente do(a) "
                                + ReceitaDAO.buscaTodos().get(receitas).getNome() + " que você deseja editar:",
                        "Editar Receita Ingredientes", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaIngrediente,
                        initialSelectionReceitaIngrediente);
                Integer receitaIngredientes = ReceitaDAO.buscaPosicaoReceitaIngrediente(receitas, (String) selectionReceitaIngrediente);

                String nomeIngredienteEditarReceitaIngrediente = ReceitaDAO.buscaTodos().get(receitas).
                        getListaIngredientes().get(receitaIngredientes).getProduto().getNome();

                do {
                    String[] opcoesEditarReceitaIngrediente = {"Ingrediente", "Quantidade", "Unidade de medida"};
                    int menuEditarReceitaIngrediente = JOptionPane.showOptionDialog(null, "Escolha a opção que você deseja editar do ingrediente do(a) "
                                    + nomeIngredienteEditarReceitaIngrediente + ":", "Editar Receita Ingrediente",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                            opcoesEditarReceitaIngrediente, opcoesEditarReceitaIngrediente[0]);

                    switch (menuEditarReceitaIngrediente) {
                        case 0: //NovoIngrediente

                            Object[] selectionValuesNovoIngrediente = ProdutoDAO.findprodutosInArrayIngrediente();
                            String initialSelectionNovoIngrediente = (String) selectionValuesNovoIngrediente[0];
                            Object selectionNovoIngrediente = JOptionPane.showInputDialog(null, "Selecione o NOVO INGREDIENTE para subtituir o(a) " +
                                            nomeIngredienteEditarReceitaIngrediente + ":", "Editar Receita Ingrediente", JOptionPane.DEFAULT_OPTION, null,
                                    selectionValuesNovoIngrediente, initialSelectionNovoIngrediente);
                            List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionNovoIngrediente);

                            ReceitaDAO.editarReceitaIngrediente(receitas, receitaIngredientes, produtos.get(0));
                            break;
                        case 1: //NovaQuantidade
                            Double novaQuantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe a NOVA QUANTIDADE do(a) "
                                            + nomeIngredienteEditarReceitaIngrediente + ":",
                                    "Editar Receita Ingrediente", JOptionPane.DEFAULT_OPTION));
                            ReceitaDAO.editarReceitaIngredienteQtd(receitas, receitaIngredientes, novaQuantidade);

                            break;
                        case 2: //NovaUnidade de medida
                            int tipoNovaUnidadeSelecionada = JOptionPane.showOptionDialog(null, "Escolha a NOVA UNIDADE DE MEDIDA do(a) " +
                                            nomeIngredienteEditarReceitaIngrediente + ":", "Editar Receita Ingrediente",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
                            ReceitaDAO.editarReceitaIngredienteUM(receitas, receitaIngredientes, opcoesUnidadeMedida[tipoNovaUnidadeSelecionada]);
                            break;
                    }

                    String[] opcoesEditarOutraReceitaIngrediente = {"Sim", "Não"};
                    int menuEditarOutraReceitaIngrediente = JOptionPane.showOptionDialog(null,
                            "Você deseja editar outra informação do(a) "
                                    + ReceitaDAO.buscaTodos().get(receitas).getListaIngredientes().get(receitaIngredientes).getProduto().getNome() +
                                    "?", "Editar Receita Ingrediente",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                            opcoesEditarOutraReceitaIngrediente, opcoesEditarOutraReceitaIngrediente[0]);
                    switch (menuEditarOutraReceitaIngrediente) {

                        case 0://SIM

                            break;
                        case 1://NÃO
                            contadorWhile = 2;
                            chamaMenuPrincipal();
                            break;
                    }
                } while (contadorWhile <= 0);

                break;

        }
    }

    public static void chamaMenuRelatorios() {
        String[] opcoesMenuRelatorio = {"Compras", "Estoque", "Receitas", "Vendas", "Voltar"};
        int menuRelatorios = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Relatorios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuRelatorio, opcoesMenuRelatorio[0]);

        switch (menuRelatorios) {

            case 0: //Compras
                ChamaRelatorioCompra();
                break;
            case 1: //Estoque
                ChamaRelatorioEstoque();
                break;
            case 2: //Receitas
                ChamaRelatorioReceitas();
                break;
            case 3: //Vendas
                ChamaRelatorioVendas();
                break;
            case 4: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void ChamaRelatorioCompra() {
        List<Compra> compras = CompraDAO.buscarTodos();
        RelatorioCompraForm.EmitirRelatorio(compras);
    }

    private static void ChamaRelatorioEstoque() {
        List<ProdutoEstoque> produtos = EstoqueDAO.buscaTodos();
        RelatorioEstoqueForm.emitirRelatorio(produtos);
    }

    private static void ChamaRelatorioReceitas() {
        List<Receita> receitas = ReceitaDAO.buscaTodos();
        RelatorioReceitaForm.emitirRelatorio(receitas);
    }

    private static void ChamaRelatorioVendas() {
        List<Venda> vendaPorData = new ArrayList<>();
        List<Venda> vendas = VendaDAO.buscarTodos();

        vendas.stream().forEach(venda -> {
            if (vendaPorData.stream().filter(venda1 -> venda1.getDataVenda().equals(venda.getDataVenda())).collect(Collectors.toList()).size() == 0) {
                vendaPorData.add(venda);
            }
        });

        RelatorioVendaForm.EmitirRelatorio(vendaPorData);
    }


    private static void chamaMenuVenda() {

        Integer contadorVenda = 0;

        FormaPagamentoEnum[] opcoesTipoPagamento = {FormaPagamentoEnum.CREDITO, FormaPagamentoEnum.DINHEIRO, FormaPagamentoEnum.DEBITO, FormaPagamentoEnum.PIX};

        Integer id = VendaDAO.aiID();

        Integer numeroComanda = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o número da comanda:",
                "Venda", JOptionPane.DEFAULT_OPTION));

        Venda venda = new Venda(id, numeroComanda, null);

        List<VendaPedido> listaReceitaCarinho = new ArrayList<>();
        Boolean excluirLastItemReceitaCarinho = false;

        do {
            Integer contadorVendaCarrinho = 0;
            String[] opcoesMenuCardapioVenda = {"Bebidas", "Entradas", "Massas", "Risotos", "Carnes", "Sobremesas", "Prato do dia", "Voltar"};
            int opcaoMenuCardapioVenda = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Venda",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCardapioVenda, opcoesMenuCardapioVenda[0]);

            switch (opcaoMenuCardapioVenda) {
                case 0: // Bebidas

                    boolean quantidadeEstoqueBebida = true;

                    Object[] selectionValuesProdutoBebida = VendaDAO.findprodutosInArrayProdutoBebida();
                    String initialSelectionProdutoBebida = (String) selectionValuesProdutoBebida[0];
                    Object selectionProdutoBebida = JOptionPane.showInputDialog(null, "Selecione a bebida",
                            "Cardápio Bebida", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutoBebida, initialSelectionProdutoBebida);
                    List<Produto> produtoBebida = VendaDAO.buscarPorNomeBebida((String) selectionProdutoBebida);

                    Integer quantidadeVendaBebida = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoBebida = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);


                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(produtoBebida.get(0), quantidadeVendaBebida, observacaoBebida));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueBebida = false;
                    }

                    VendaPedido vendaPedidoBebida = new VendaPedido(produtoBebida.get(0), quantidadeVendaBebida, observacaoBebida);

                    do {
                        String[] opcoesMenuVendaBebida = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho Venda"};
                        int menuCadastroBebida = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaBebida, opcoesMenuVendaBebida[0]);

                        switch (menuCadastroBebida) {
                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;
                                if (quantidadeEstoqueBebida == true) {
                                    venda.adicionarVendaPedido(vendaPedidoBebida);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueBebida == true) {
                                    venda.adicionarVendaPedido(vendaPedidoBebida);
                                }

                                int tipoPagamentoSelecionadoBebida = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoBebida]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;
                            case 3: //Carrinho de venda

                                if (quantidadeEstoqueBebida == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoBebida, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }

                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;
                case 1: // Entrada
                    boolean quantidadeEstoqueEntrada = true;

                    Object[] selectionValuesReceitaEntrada = VendaDAO.findreceitaInArrayReceitaEntrada();
                    String initialSelectionReceitaEntrada = (String) selectionValuesReceitaEntrada[0];
                    Object selectionReceitaEntrada = JOptionPane.showInputDialog(null, "Selecione a entrada",
                            "Cardápio Entrada", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaEntrada, initialSelectionReceitaEntrada);
                    List<Receita> receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaEntrada);

                    Integer quantidadeVendaEntrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoEntrada = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);

                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(receita.get(0), quantidadeVendaEntrada, observacaoEntrada));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueEntrada = false;
                    }

                    VendaPedido vendaPedidoEntrada = new VendaPedido(receita.get(0), quantidadeVendaEntrada, observacaoEntrada);

                    contadorVendaCarrinho = 0;
                    do {
                        String[] opcoesMenuVendaEntrada = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho Venda"};
                        int menuCadastroEntrada = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaEntrada, opcoesMenuVendaEntrada[0]);

                        switch (menuCadastroEntrada) {
                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;
                                if (quantidadeEstoqueEntrada == true) {
                                    venda.adicionarVendaPedido(vendaPedidoEntrada);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueEntrada == true) {
                                    venda.adicionarVendaPedido(vendaPedidoEntrada);
                                }

                                int tipoPagamentoSelecionadoEntrada = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoEntrada]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;

                            case 3: //Carrinho de venda

                                if (quantidadeEstoqueEntrada == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoEntrada, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }

                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;

                case 2: // Massas
                    boolean quantidadeEstoqueMassa = true;

                    Object[] selectionValuesReceitaMassa = VendaDAO.findreceitaInArrayReceitaMassa();
                    String initialSelectionReceitaMassa = (String) selectionValuesReceitaMassa[0];
                    Object selectionReceitaMassa = JOptionPane.showInputDialog(null, "Selecione a massa",
                            "Cardápio Massa", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaMassa, initialSelectionReceitaMassa);
                    receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaMassa);

                    Integer quantidadeVendaMassa = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoMassa = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);

                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(receita.get(0), quantidadeVendaMassa, observacaoMassa));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueMassa = false;
                    }

                    VendaPedido vendaPedidoMassa = new VendaPedido(receita.get(0), quantidadeVendaMassa, observacaoMassa);

                    contadorVendaCarrinho = 0;

                    do {
                        String[] opcoesMenuVendaMassa = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho Venda"};
                        int menuCadastroMassa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaMassa, opcoesMenuVendaMassa[0]);

                        switch (menuCadastroMassa) {
                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;
                                if (quantidadeEstoqueMassa == true) {
                                    venda.adicionarVendaPedido(vendaPedidoMassa);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueMassa == true) {
                                    venda.adicionarVendaPedido(vendaPedidoMassa);
                                }

                                int tipoPagamentoSelecionado = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionado]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;

                            case 3: //Carinho de venda

                                if (quantidadeEstoqueMassa == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoMassa, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }
                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;
                case 3: // Risotos

                    boolean quantidadeEstoqueRisoto = true;

                    Object[] selectionValuesReceitaRisoto = VendaDAO.findreceitaInArrayReceitaRisoto();
                    String initialSelectionReceitaRisoto = (String) selectionValuesReceitaRisoto[0];
                    Object selectionReceitaRisoto = JOptionPane.showInputDialog(null, "Selecione o risoto",
                            "Cardapio Risoto", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaRisoto, initialSelectionReceitaRisoto);
                    receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaRisoto);

                    Integer quantidadeVendaRisoto = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoRisoto = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);

                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(receita.get(0), quantidadeVendaRisoto, observacaoRisoto));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueRisoto = false;
                    }

                    VendaPedido vendaPedidoRisoto = new VendaPedido(receita.get(0), quantidadeVendaRisoto, observacaoRisoto);

                    contadorVendaCarrinho = 0;

                    do {
                        String[] opcoesMenuVendaRisoto = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho de Venda"};
                        int menuCadastroRisoto = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaRisoto, opcoesMenuVendaRisoto[0]);

                        switch (menuCadastroRisoto) {
                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;
                                if (quantidadeEstoqueRisoto == true) {
                                    venda.adicionarVendaPedido(vendaPedidoRisoto);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueRisoto == true) {
                                    venda.adicionarVendaPedido(vendaPedidoRisoto);
                                }

                                int tipoPagamentoSelecionadoRisotos = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoRisotos]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;
                            case 3: //Carrinho de venda

                                if (quantidadeEstoqueRisoto == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoRisoto, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }
                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;

                case 4: // Carnes
                    boolean quantidadeEstoqueCarne = true;

                    Object[] selectionValuesReceitaCarne = VendaDAO.findreceitaInArrayReceitaCarne();
                    String initialSelectionReceitaCarne = (String) selectionValuesReceitaCarne[0];
                    Object selectionReceitaCarne = JOptionPane.showInputDialog(null, "Selecione a carne:",
                            "Cardapio Carne", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaCarne, initialSelectionReceitaCarne);
                    receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaCarne);

                    Integer quantidadeVendaCarne = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade:",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoCarne = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);

                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(receita.get(0), quantidadeVendaCarne, observacaoCarne));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueCarne = false;
                    }

                    VendaPedido vendaPedidoCarne = new VendaPedido(receita.get(0), quantidadeVendaCarne, observacaoCarne);

                    contadorVendaCarrinho = 0;

                    do {
                        String[] opcoesMenuVendaCarne = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho de Venda"};
                        int menuCadastroCarne = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaCarne, opcoesMenuVendaCarne[0]);

                        switch (menuCadastroCarne) {
                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;
                                if (quantidadeEstoqueCarne == true) {
                                    venda.adicionarVendaPedido(vendaPedidoCarne);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueCarne == true) {
                                    venda.adicionarVendaPedido(vendaPedidoCarne);
                                }

                                int tipoPagamentoSelecionadoCarnes = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoCarnes]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;
                            case 3: //Carinho de venda

                                if (quantidadeEstoqueCarne == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoCarne, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }

                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;
                case 5: // Sobremesas

                    boolean quantidadeEstoqueSobremesa = true;

                    Object[] selectionValuesReceitaSobremesa = VendaDAO.findreceitaInArrayReceitaSobremesa();
                    String initialSelectionReceitaSobremesa = (String) selectionValuesReceitaSobremesa[0];
                    Object selectionReceitaSobremesa = JOptionPane.showInputDialog(null, "Selecione a sobremesa",
                            "Venda", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaSobremesa, initialSelectionReceitaSobremesa);
                    receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaSobremesa);

                    Integer quantidadeVendaSobremesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                            "Venda", JOptionPane.DEFAULT_OPTION));

                    String observacaoSobremesa = JOptionPane.showInputDialog(null, "Observação:",
                            "Venda", JOptionPane.DEFAULT_OPTION);

                    if (excluirLastItemReceitaCarinho == true) {
                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                        excluirLastItemReceitaCarinho = false;
                    }

                    listaReceitaCarinho.add(new VendaPedido(receita.get(0), quantidadeVendaSobremesa, observacaoSobremesa));

                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 1) {
                        JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                        quantidadeEstoqueSobremesa = false;
                    }

                    VendaPedido vendaPedidoSobremesa = new VendaPedido(receita.get(0), quantidadeVendaSobremesa, observacaoSobremesa);

                    contadorVendaCarrinho = 0;

                    do {
                        String[] opcoesMenuVendaSobremesa = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda", "Carrinho de Venda"};
                        int menuCadastroVendaSobremesa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                "Venda",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaSobremesa, opcoesMenuVendaSobremesa[0]);

                        switch (menuCadastroVendaSobremesa) {

                            case 0: //ContinuarPedindo
                                contadorVendaCarrinho = 4;

                                if (quantidadeEstoqueSobremesa == true) {
                                    venda.adicionarVendaPedido(vendaPedidoSobremesa);
                                } else {
                                    excluirLastItemReceitaCarinho = true;
                                }

                                break;
                            case 1: //CancelarPedido
                                contadorVenda = 2;
                                contadorVendaCarrinho = 2;
                                chamaMenuPrincipal();
                                break;
                            case 2: //FinalizaPedido
                                if (quantidadeEstoqueSobremesa == true) {
                                    venda.adicionarVendaPedido(vendaPedidoSobremesa);
                                }

                                int tipoPagamentoSelecionadoSobremesa = JOptionPane.showOptionDialog(null,
                                        "VALOR TOTAL: R$" + venda.calculaValorListaVenda(venda) + "\nEscolha a forma de pagamento:", "Venda",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoSobremesa]);
                                VendaDAO.salvarListaVenda(venda);

                                EstoqueDAO.baixaVendaEstoque(venda);

                                contadorVenda = 1;
                                contadorVendaCarrinho = 1;
                                chamaMenuPrincipal();

                                break;
                            case 3: //Carrinho de venda
                                if (quantidadeEstoqueSobremesa == true) {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), vendaPedidoSobremesa, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                } else {
                                    Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                            (venda.getListaVendaPedido(), null, venda.calculaValorListaVenda(venda));
                                    JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                            "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }
                                break;
                        }
                    } while (contadorVendaCarrinho <= 0);
                    break;

                case 6: // Prato do dia
                    contadorVenda = 1;
                    break;
                case 7: // Voltar
                    contadorVenda = 1;
                    chamaMenuPrincipal();
                    break;
            }
        } while (contadorVenda <= 0);

    }
}

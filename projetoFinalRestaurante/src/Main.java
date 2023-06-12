import model.*;
import repository.*;


import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Main {


    public static void main(String[] args) {
        ProdutoDAO.inputProdutos();
        CompraDAO.inputCompras();
        ReceitaDAO.inputReceita();
        PlanejamentoProducaoDAO.inputPlanejamento();
        EstoqueDAO.inputEstoque();
        //VendaDAO.inputVendas();
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

//    private static void cadastroDeCliente() {
//        String nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");
//        String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do cliente");
//        String email = JOptionPane.showInputDialog(null, "Digite o email do cliente");
//        ClienteExemplo cliente = new ClienteExemplo(nome, cpf, email);
//        ClienteDAOExemplo.salvar(cliente);
//        chamaMenuPrincipal();
//    }

    private static void chamaMenuPlanejamento() {
        String[] opcoesMenuPlanejamento = {"Cadastrar planejamento", "Planejamento", "Prato do dia", "Voltar"};
        int menuPlanejamento = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Cadastrar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPlanejamento, opcoesMenuPlanejamento[0]);

        switch (menuPlanejamento) {
            case 0: //Cadastrar Planejamento
                cadastroPlanejamento();
                break;
            case 1: //Planejado
                break;
            case 2: // planeja
                selecionaPratoDoDia();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void cadastroPlanejamento() {
        LocalDate dataPlanejamento = LocalDate.now();
        String inputData = JOptionPane.showInputDialog(null, "Digite uma data (formato: dd/MM/yyyy):",
                "Cadastrar Planejamento", JOptionPane.DEFAULT_OPTION);
        try {
            dataPlanejamento = LocalDate.parse(inputData, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido!",
                    "Cadastrar Planejamento", JOptionPane.ERROR_MESSAGE);
            chamaMenuPlanejamento();
        }
    }
    private static  void selecionaPratoDoDia() {

        Object[] selectionValuesReceita = VendaDAO.findreceitaInArrayReceitaEntrada();
        String initialSelectionReceita = (String) selectionValuesReceita[0];
        Object selectionReceitaEntrada = JOptionPane.showInputDialog(null, "Selecione o prato do dia",
                "Opções", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceita, initialSelectionReceita);
        List<Receita> receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaEntrada);
        Integer quantidadeVendaEntrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade desejada",
                "Prato do dia", JOptionPane.DEFAULT_OPTION));

        VendaPedido vendaPedidoEntrada = new VendaPedido(receita.get(0),quantidadeVendaEntrada);
        //
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
    private static void menuCadastroProduto(){
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

        Integer id = ProdutoDAO.aiID();

        ProdutoEnum[] opcoesTipoPagamento = {ProdutoEnum.BEBIDA, ProdutoEnum.INGREDIENTE};

        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastro Produto Ingrediente", JOptionPane.DEFAULT_OPTION);

        Produto produto1 = new Produto(id, nomeProduto, opcoesTipoPagamento[1]);


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

        Integer id = ProdutoDAO.aiID();
        String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastro Produto Bebida", JOptionPane.DEFAULT_OPTION);

        BigDecimal valorCustoBebida = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null,"Infome o valor de custo:",
                "Cadastrar Produto Bebida", JOptionPane.DEFAULT_OPTION)));

        Produto produto1 = new Produto(id,nomeProduto,opcoesTipoProduto[0],valorCustoBebida);

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
            Object selectionProduto = JOptionPane.showInputDialog(null, "Selecione o produto da compra",
                    "Cadastrar Compra", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutos, initialSelectionProduto);
            List<Produto> produtos = ProdutoDAO.buscarPorNome((String) selectionProduto);

            Double quantidade = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite a quantidade",
                    "Cadastrar Compra", JOptionPane.DEFAULT_OPTION));
            int tipoUnidadeSelecionado = JOptionPane.showOptionDialog(null, "Informe a unidade de medida:",
                    "Cadastrar Compra",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesUnidadeMedida, opcoesUnidadeMedida[0]);
            Compra compra = new Compra(id, dataCompra, produtos.get(0), quantidade, opcoesUnidadeMedida[tipoUnidadeSelecionado]);
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

        Integer id = ReceitaDAO.aiID();
        String nome = JOptionPane.showInputDialog(null, "Informe o nome:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION);
        int receitaClasseSelecionado = JOptionPane.showOptionDialog(null, "Escolha a classe da receita:",
                "Cadastrar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesReceitaClasse, opcoesReceitaClasse[0]);
        BigDecimal valorCusto = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null, "Infome o valor de custo:",
                "Cadastrar Receita", JOptionPane.DEFAULT_OPTION)));

        Receita receita1 = new Receita(id, nome, opcoesReceitaClasse[receitaClasseSelecionado], valorCusto);

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

        String[] opcoesEditarReceita = {"ID", "Nome", "Classe", "Valor de custo", "Ingredientes"};
        int menuEditarReceita = JOptionPane.showOptionDialog(null, "Escolha a opção que você deseja editar do(a) " +
                        ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesEditarReceita, opcoesEditarReceita[0]);

        switch (menuEditarReceita) {
            case 0: //editarReceitaID
                Integer id = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o NOVO ID da(o) " +
                        ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita", JOptionPane.DEFAULT_OPTION));
                ReceitaDAO.editarReceitaId(receitas, id);
                chamaMenuPrincipal();
                break;
            case 1: //editarReceitaNome
                String nome = JOptionPane.showInputDialog(null, "Informe o NOVO NOME para substituir "
                        + ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita", JOptionPane.DEFAULT_OPTION);
                ReceitaDAO.editarReceitaNome(receitas, nome);
                chamaMenuPrincipal();
                break;
            case 2: //editarReceitaClasse
                int receitaClasseSelecionado = JOptionPane.showOptionDialog(null, "Escolha a NOVA CLASSE do(a) " +
                                ReceitaDAO.buscaTodos().get(receitas).getNome() + ":", "Editar Receita",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesReceitaClasse, opcoesReceitaClasse[0]);
                ReceitaDAO.editarReceitaClasse(receitas, opcoesReceitaClasse[receitaClasseSelecionado]);
                chamaMenuPrincipal();
            case 3: //editarReceitaValorCusto
                BigDecimal valorCusto = BigDecimal.valueOf(Double.parseDouble(JOptionPane.showInputDialog(null,
                        "Informe o NOVO VALOR DE CUSTO do(a) " + ReceitaDAO.buscaTodos().get(receitas).getNome() + ":",
                        "Editar Receita", JOptionPane.DEFAULT_OPTION)));
                ReceitaDAO.editarReceitaValorCusto(receitas, valorCusto);
                chamaMenuPrincipal();
                break;

            case 4: // editarReceitaIngredientes
                Integer contadorWhile = 0;

                UnidadeMedidaEnum[] opcoesUnidadeMedida = {UnidadeMedidaEnum.KILO, UnidadeMedidaEnum.GRAMA, UnidadeMedidaEnum.LITRO,
                        UnidadeMedidaEnum.MILILITRO, UnidadeMedidaEnum.UNIDADE};

                Object[] selectionValuesReceitaIngrediente = ReceitaDAO.findReceitasIngredientesInArray(((String) selectionProduto));
                String initialSelectionReceitaIngrediente = (String) selectionValuesReceita[0];
                Object selectionReceitaIngrediente = JOptionPane.showInputDialog(null, "Selecione o ingrediente da "
                                + ReceitaDAO.buscaTodos().get(receitas).getNome() + " que você deseja editar:",
                        "Editar Receita Ingredientes", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaIngrediente,
                        initialSelectionReceitaIngrediente);
                Integer receitaIngredientes = ReceitaDAO.buscaPosicaoReceitaIngrediente(receitas, (String) selectionReceitaIngrediente);

                String nomeIngredienteEditarReceitaIngrediente = ReceitaDAO.buscaTodos().get(receitas).
                        getListaIngredientes().get(receitaIngredientes).getProduto().getNome();

                do {
                    String[] opcoesEditarReceitaIngrediente = {"Ingrediente", "Quantidade", "Unidade de medida"};
                    int menuEditarReceitaIngrediente = JOptionPane.showOptionDialog(null, "Escolha a opção que você deseja editar do(a) "
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
                JOptionPane.showMessageDialog(null, ReceitaDAO.buscaTodos());
                chamaMenuPrincipal();
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


    private static void chamaMenuVenda() {

        Integer contadorVenda = 0;

        FormaPagamentoEnum[] opcoesTipoPagamento = {FormaPagamentoEnum.CREDITO, FormaPagamentoEnum.DINHEIRO, FormaPagamentoEnum.DEBITO, FormaPagamentoEnum.PIX};

        Integer id = VendaDAO.aiID();

        Integer numeroComanda = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o número da comanda:",
                "Venda", JOptionPane.DEFAULT_OPTION));

        Venda venda = new Venda(id, numeroComanda,null);

        List<VendaPedido> listaReceitaCarinho = new ArrayList<>();
        Integer contadorVendaCarrinho = 0;

        do {   String[] opcoesMenuCardapioVenda = {"Bebidas","Entradas" , "Massas", "Risotos", "Carnes", "Sobremesas", "Prato do dia", "Voltar"};
                int opcaoMenuCardapioVenda = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Venda",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCardapioVenda, opcoesMenuCardapioVenda[0]);

                    switch (opcaoMenuCardapioVenda) {
                        case 0: // Bebidas
                              Object[] selectionValuesProdutoBebida = VendaDAO.findprodutosInArrayProdutoBebida();
                            String initialSelectionProdutoBebida = (String) selectionValuesProdutoBebida[0];
                            Object selectionProdutoBebida = JOptionPane.showInputDialog(null, "Selecione a bebida",
                                    "Cardápio Bebida", JOptionPane.DEFAULT_OPTION, null, selectionValuesProdutoBebida, initialSelectionProdutoBebida);
                            List<Produto> produtoBebida = VendaDAO.buscarPorNomeBebida((String) selectionProdutoBebida);

                            Integer quantidadeVendaBebida = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                                    "Venda", JOptionPane.DEFAULT_OPTION));

                          listaReceitaCarinho.add(new VendaPedido(produtoBebida.get(0),quantidadeVendaBebida));

                          if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1){
                              JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                      "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                          }

                            VendaPedido vendaPedidoBebida = new VendaPedido(produtoBebida.get(0),quantidadeVendaBebida);

                            do { String[] opcoesMenuVendaBebida = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho Venda"};
                            int menuCadastroBebida = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                    "Venda",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaBebida, opcoesMenuVendaBebida[0]);

                            switch (menuCadastroBebida) {
                                case 0: //ContinuarPedindo
                                    contadorVendaCarrinho = 4;
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoBebida);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    break;
                                case 1: //CancelarPedido
                                    contadorVenda = 2;
                                    contadorVendaCarrinho = 2;
                                    chamaMenuPrincipal();
                                    break;
                                case 2: //FinalizaPedido
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoBebida);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    int tipoPagamentoSelecionadoBebida = JOptionPane.showOptionDialog(null,
                                            "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:","Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                    venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoBebida]);
                                    VendaDAO.salvarListaVenda(venda);

                                    JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    contadorVenda = 1;
                                    contadorVendaCarrinho = 1;
                                    chamaMenuPrincipal();

                                    break;
                                case 3: //Carrinho de venda

                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),vendaPedidoBebida,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    }

                                    break;
                                    }
                                } while (contadorVendaCarrinho<=0);
                        break;
                        case 1: // Entrada
                            Object[] selectionValuesReceitaEntrada = VendaDAO.findreceitaInArrayReceitaEntrada();
                            String initialSelectionReceitaEntrada = (String) selectionValuesReceitaEntrada[0];
                            Object selectionReceitaEntrada = JOptionPane.showInputDialog(null, "Selecione a entrada",
                                    "Cardápio Entrada", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaEntrada, initialSelectionReceitaEntrada);
                            List<Receita> receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaEntrada);

                            Integer quantidadeVendaEntrada = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                                    "Venda", JOptionPane.DEFAULT_OPTION));

                            listaReceitaCarinho.add(new VendaPedido(receita.get(0),quantidadeVendaEntrada));

                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1){
                                JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                        "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                            }

                            VendaPedido vendaPedidoEntrada = new VendaPedido(receita.get(0),quantidadeVendaEntrada);

                            contadorVendaCarrinho = 0;
                            do { String[] opcoesMenuVendaEntrada = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho Venda"};
                            int menuCadastroEntrada = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                    "Venda",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaEntrada, opcoesMenuVendaEntrada[0]);

                            switch (menuCadastroEntrada) {
                                case 0: //ContinuarPedindo
                                    contadorVendaCarrinho = 4;
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoEntrada);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    break;
                                case 1: //CancelarPedido
                                    contadorVenda = 2;
                                    contadorVendaCarrinho = 2;
                                    chamaMenuPrincipal();
                                    break;
                                case 2: //FinalizaPedido
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoEntrada);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    int tipoPagamentoSelecionadoEntrada = JOptionPane.showOptionDialog(null,
                                            "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:","Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                    venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoEntrada]);
                                    VendaDAO.salvarListaVenda(venda);

                                    JOptionPane.showConfirmDialog(null, "Venda concluída com sucesso!",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    contadorVenda = 1;
                                    contadorVendaCarrinho = 1;
                                    chamaMenuPrincipal();

                                    break;

                                case 3: //Carrinho de venda

                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),vendaPedidoEntrada,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    }

                                    break;
                                    }
                                } while (contadorVendaCarrinho<=0);
                            break;

                        case 2: // Massas
                            Object[] selectionValuesReceitaMassa = VendaDAO.findreceitaInArrayReceitaMassa();
                            String initialSelectionReceitaMassa = (String) selectionValuesReceitaMassa[0];
                            Object selectionReceitaMassa = JOptionPane.showInputDialog(null, "Selecione a massa",
                                    "Cardápio Massa", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaMassa, initialSelectionReceitaMassa);
                            receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaMassa);

                            Integer quantidadeVendaMassa = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                                    "Venda", JOptionPane.DEFAULT_OPTION));

                            listaReceitaCarinho.add(new VendaPedido(receita.get(0),quantidadeVendaMassa));

                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1) {
                                JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                        "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                            }



                            VendaPedido vendaPedidoMassa = new VendaPedido(receita.get(0),quantidadeVendaMassa);

                            contadorVendaCarrinho = 0;

                           do { String[] opcoesMenuVendaMassa = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho Venda"};
                            int menuCadastroMassa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                    "Venda",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaMassa, opcoesMenuVendaMassa[0]);

                            switch (menuCadastroMassa) {
                                case 0: //ContinuarPedindo
                                    contadorVendaCarrinho = 4;
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoMassa);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    break;
                                case 1: //CancelarPedido
                                    contadorVenda = 2;
                                    contadorVendaCarrinho = 2;
                                    chamaMenuPrincipal();
                                    break;
                                case 2: //FinalizaPedido
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoMassa);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    int tipoPagamentoSelecionado = JOptionPane.showOptionDialog(null,
                                            "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:","Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                    venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionado]);
                                    VendaDAO.salvarListaVenda(venda);

                                    JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    contadorVenda = 1;
                                    contadorVendaCarrinho = 1;
                                    chamaMenuPrincipal();

                                    break;

                                case 3: //Carinho de venda

                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),vendaPedidoMassa,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    }

                                    break;
                                    }
                                } while (contadorVendaCarrinho<=0);
                            break;

                        case 3: // Risotos
                            Object[] selectionValuesReceitaRisoto = VendaDAO.findreceitaInArrayReceitaRisoto();
                            String initialSelectionReceitaRisoto = (String) selectionValuesReceitaRisoto[0];
                            Object selectionReceitaRisoto = JOptionPane.showInputDialog(null, "Selecione o risoto",
                                    "Cardapio Risoto", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaRisoto, initialSelectionReceitaRisoto);
                            receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaRisoto);

                            Integer quantidadeVendaRisoto = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                                    "Venda", JOptionPane.DEFAULT_OPTION));

                            listaReceitaCarinho.add(new VendaPedido(receita.get(0),quantidadeVendaRisoto));

                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1){
                                JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                        "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                            }

                            VendaPedido vendaPedidoRisoto = new VendaPedido(receita.get(0),quantidadeVendaRisoto);

                            contadorVendaCarrinho = 0;

                           do { String[] opcoesMenuVendaRisoto = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho de Venda"};
                            int menuCadastroRisoto = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                    "Venda",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaRisoto, opcoesMenuVendaRisoto[0]);

                            switch (menuCadastroRisoto) {
                                case 0: //ContinuarPedindo
                                    contadorVendaCarrinho = 4;
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoRisoto);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    break;
                                case 1: //CancelarPedido
                                    contadorVenda = 2;
                                    contadorVendaCarrinho = 2;
                                    chamaMenuPrincipal();
                                    break;
                                case 2: //FinalizaPedido
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoRisoto);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    int tipoPagamentoSelecionadoRisotos = JOptionPane.showOptionDialog(null,
                                            "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:","Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                    venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoRisotos]);
                                    VendaDAO.salvarListaVenda(venda);

                                    JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    contadorVenda = 1;
                                    contadorVendaCarrinho = 1;
                                    chamaMenuPrincipal();

                                    break;
                                case 3: //Carrinho de venda

                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),vendaPedidoRisoto,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    }

                                    break;
                                    }
                                } while (contadorVendaCarrinho<=0);
                            break;

                        case 4: // Carnes
                            Object[] selectionValuesReceitaCarne = VendaDAO.findreceitaInArrayReceitaCarne();
                            String initialSelectionReceitaCarne = (String) selectionValuesReceitaCarne[0];
                            Object selectionReceitaCarne = JOptionPane.showInputDialog(null, "Selecione a carne:",
                                    "Cardapio Carne", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaCarne, initialSelectionReceitaCarne);
                            receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaCarne);

                            Integer quantidadeVendaCarne = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade:",
                                    "Venda", JOptionPane.DEFAULT_OPTION));

                            listaReceitaCarinho.add(new VendaPedido(receita.get(0),quantidadeVendaCarne));

                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1){
                                JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                        "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                            }

                            VendaPedido vendaPedidoCarne = new VendaPedido(receita.get(0),quantidadeVendaCarne);

                            contadorVendaCarrinho = 0;

                           do { String[] opcoesMenuVendaCarne = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho de Venda"};
                            int menuCadastroCarne = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                    "Venda",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaCarne, opcoesMenuVendaCarne[0]);

                            switch (menuCadastroCarne) {
                                case 0: //ContinuarPedindo
                                    contadorVendaCarrinho = 4;
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoCarne);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    break;
                                case 1: //CancelarPedido
                                    contadorVenda = 2;
                                    contadorVendaCarrinho = 2;
                                    chamaMenuPrincipal();
                                    break;
                                case 2: //FinalizaPedido
                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        venda.adicionarVendaPedido(vendaPedidoCarne);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                    }

                                    int tipoPagamentoSelecionadoCarnes = JOptionPane.showOptionDialog(null,
                                            "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:", "Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                    venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoCarnes]);
                                    VendaDAO.salvarListaVenda(venda);

                                    JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    contadorVenda = 1;
                                    contadorVendaCarrinho = 1;
                                    chamaMenuPrincipal();

                                    break;
                                case 3: //Carinho de venda

                                    if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),vendaPedidoCarne,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    } else {
                                        listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                        Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                        JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                    }

                                    break;
                                  }
                               } while (contadorVendaCarrinho<=0);
                            break;
                        case 5: // Sobremesas


                                Object[] selectionValuesReceitaSobremesa = VendaDAO.findreceitaInArrayReceitaSobremesa();
                                String initialSelectionReceitaSobremesa = (String) selectionValuesReceitaSobremesa[0];
                                Object selectionReceitaSobremesa = JOptionPane.showInputDialog(null, "Selecione a sobremesa",
                                        "Venda", JOptionPane.DEFAULT_OPTION, null, selectionValuesReceitaSobremesa, initialSelectionReceitaSobremesa);
                                receita = VendaDAO.buscarPorNomeReceita((String) selectionReceitaSobremesa);

                                Integer quantidadeVendaSobremesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe a quantidade",
                                        "Venda", JOptionPane.DEFAULT_OPTION));

                            listaReceitaCarinho.add(new VendaPedido(receita.get(0),quantidadeVendaSobremesa));

                                if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho)== 1){
                                    JOptionPane.showConfirmDialog(null, "Estoque Insuficiente! ",
                                            "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                }

                            VendaPedido vendaPedidoSobremesa = new VendaPedido(receita.get(0),quantidadeVendaSobremesa);

                            contadorVendaCarrinho = 0;

                                  do{  String[] opcoesMenuVendaSobremesa = {"Adicionar Novo Item", "Cancelar Venda", "Finalizar Venda","Carrinho de Venda"};
                                    int menuCadastroVendaSobremesa = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                                            "Venda",
                                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuVendaSobremesa, opcoesMenuVendaSobremesa[0]);

                                    switch (menuCadastroVendaSobremesa) {

                                        case 0: //ContinuarPedindo
                                            contadorVendaCarrinho = 4;

                                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                            venda.adicionarVendaPedido(vendaPedidoSobremesa);
                                            } else {
                                                listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                            }

                                            // esse esta certo , colocar em todos os outros e fazer a att do metodo de verificação
                                            // para bebida tambem.

                                            break;
                                        case 1: //CancelarPedido
                                            contadorVenda = 2;
                                            contadorVendaCarrinho = 2;
                                            chamaMenuPrincipal();
                                            break;
                                        case 2: //FinalizaPedido
                                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                                venda.adicionarVendaPedido(vendaPedidoSobremesa);
                                            } else {
                                                listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                            }

                                            int tipoPagamentoSelecionadoSobremesa = JOptionPane.showOptionDialog(null,
                                                    "VALOR TOTAL: R$"+venda.calculaValorListaVenda(venda)+"\nEscolha a forma de pagamento:", "Venda",
                                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesTipoPagamento, opcoesTipoPagamento[0]);
                                            venda.setFormaPagamento(opcoesTipoPagamento[tipoPagamentoSelecionadoSobremesa]);
                                            VendaDAO.salvarListaVenda(venda);

                                            JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                                                    "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                            contadorVenda = 1;
                                            contadorVendaCarrinho = 1;
                                            chamaMenuPrincipal();

                                            break;
                                        case 3: //Carrinho de venda

                                            if (EstoqueDAO.verificaDisponibilidade(listaReceitaCarinho) == 0){
                                                Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                        (venda.getListaVendaPedido(),vendaPedidoSobremesa,venda.calculaValorListaVenda(venda));
                                                JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                        "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                            } else {
                                                listaReceitaCarinho.remove(listaReceitaCarinho.size() - 1);
                                                Object[] selectionValuesReceitaCarinho = VendaDAO.findreceitaInArrayReceitaCarinho
                                                        (venda.getListaVendaPedido(),null,venda.calculaValorListaVenda(venda));
                                                JOptionPane.showConfirmDialog(null, selectionValuesReceitaCarinho,
                                                        "Carrinho de Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                                            }

                                            break;
                                    }
                                  } while (contadorVendaCarrinho<=0);
                            break;

                        case 6: // Prato do dia
                            contadorVenda = 1;
                            break;
                        case 7: // Voltar
                            contadorVenda = 1;
                            chamaMenuPrincipal();
                            break;
                            }
                        } while (contadorVenda<=0);
    }
}
package repository;

import model.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements InterfaceAutoIncrement {

    static List<Produto> listaProdutos = new ArrayList<>();

    public static void inputProdutos() {
        if (ProdutoDAO.listaProdutos.isEmpty()) {
            listaProdutos.add(new Produto(1, "Massa caseira Zezé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(2, "Bacon", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(3, "Coca-cola", ProdutoEnum.BEBIDA, BigDecimal.valueOf(3)));
            listaProdutos.add(new Produto(4, "Cebolinha verde", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(5, "Ovo", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(6, "Creme de leite", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(7, "Coca-zero", ProdutoEnum.BEBIDA, BigDecimal.valueOf(3)));
            listaProdutos.add(new Produto(8, "Água com gás", ProdutoEnum.BEBIDA, BigDecimal.valueOf(2)));
            listaProdutos.add(new Produto(9, "Água sem gás", ProdutoEnum.BEBIDA, BigDecimal.valueOf(2)));
            listaProdutos.add(new Produto(10, "Suco de Uva", ProdutoEnum.BEBIDA, BigDecimal.valueOf(4)));
            listaProdutos.add(new Produto(11, "Cerveja Heineken", ProdutoEnum.BEBIDA, BigDecimal.valueOf(8)));
            listaProdutos.add(new Produto(12, "Cerveja Original", ProdutoEnum.BEBIDA, BigDecimal.valueOf(7)));
            listaProdutos.add(new Produto(13, "Batata", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(14, "Camarão", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(15, "Gnocchi caseiro Zezé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(16, "Queijo Muçarela", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(17, "Queijo Parmesão", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(18, "Queijo Provolone", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(19, "Queijo Gorgonzola", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(20, "Arroz arbóreo caseiro Zezé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(21, "Molho de tomate", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(22, "Tomate", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(23, "Lula", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(24, "Marisco", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(25, "Siri", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(26, "Picanha", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(27, "Rúcula", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(28, "Manteiga", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(29, "Piadina caseira Zezé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(30, "Nutella", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(31, "Sovete Creme Kibon", ProdutoEnum.INGREDIENTE));
        }
    }

    public static void salvarListaProdutos(Produto produto) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produto.setId(produtoDAO.geraID());
        listaProdutos.add(produto);
    }

    public static Integer removerProduto(Produto produto) {
        
        if (produto.getTipoProduto().equals(ProdutoEnum.INGREDIENTE)) {
            for (int x = 0; x < ReceitaDAO.listaReceita.size(); x++) {
                for (int y = 0; y < ReceitaDAO.listaReceita.get(x).getListaIngredientes().size(); y++){
                    if (ReceitaDAO.listaReceita.get(x).getListaIngredientes().get(y).getProduto() == produto){
                        return JOptionPane.showConfirmDialog(null,
                            "Não é possivel remover um produto que existe em alguma receita !!!",
                            "Remover Produto", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
                  }
                }
            }
        }

        EstoqueDAO.removerProdutoEstoque(produto);
        listaProdutos.remove(produto);

        return JOptionPane.showConfirmDialog(null, "Produto excluido com sucesso!\nEstoque atualizado!",
                "Remover Produto", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static List<Produto> buscaTodos() {
        return listaProdutos;
    }

    public static List<Produto> buscarPorNome(String nome) {
        List<Produto> produtosFiltradas = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            if (produto.getNome().contains(nome)) {
                produtosFiltradas.add(produto);
            }
        }
        return produtosFiltradas;
    }

    public static Object[] findprodutosInArray() {
        List<Produto> produtos = buscaTodos();
        List<String> produtosNomes = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosNomes.add(produto.getNome());
        }
        return produtosNomes.toArray();
    }

    public static Object[] findprodutosInArrayIngrediente() {
        List<Produto> produtos = buscaTodos();
        List<String> produtosNomes = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getTipoProduto() == ProdutoEnum.INGREDIENTE) {
                produtosNomes.add(produto.getNome());
            }
        }
        return produtosNomes.toArray();
    }

    public static List<Produto> buscarBebidas() {
        List<Produto> produtosBebidas = new ArrayList<>();
        for (Produto produto : ProdutoDAO.listaProdutos) {
            if (produto.getTipoProduto() == ProdutoEnum.BEBIDA) {
                produtosBebidas.add(produto);
            }
        }
        return produtosBebidas;
    }

    @Override
    public Integer geraID() {
        Integer id = listaProdutos.size() + 1;
        return id;
    }
}


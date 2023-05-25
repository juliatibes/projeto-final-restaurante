package repository;
import model.Produto;
import model.ProdutoEnum;
import model.UnidadeMedidaEnum;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    static List<Produto> listaProdutos = new ArrayList<>();


    public ProdutoDAO() {
        inputProdutos();
    }

    public static void inputProdutos(){
        if (ProdutoDAO.listaProdutos.isEmpty()) {
            listaProdutos.add(new Produto(1, "Massa caseira zézé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(2, "Bacon", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(3, "Coca-cola 2L", ProdutoEnum.BEBIBA));
            listaProdutos.add(new Produto(4, "Cebolinha Verde", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(5, "ovo", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(6, "Creme de leite", ProdutoEnum.INGREDIENTE));
        }
    }

    public static void salvarListaProdutos(Produto produto) {
        listaProdutos.add(produto);
    }

    public static Integer removerProduto(Produto produto){
        listaProdutos.remove(produto);
        return JOptionPane.showConfirmDialog(null,"Produto excluido com sucesso!",
                "Remover Produto",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION,null);
    }

    public static List<Produto> buscaTodos() {
        return listaProdutos;
    }
        public static List<Produto> buscarPorNome(String nome) {
            List<Produto> produtosFiltradas = new ArrayList<>();
            for (Produto produto : listaProdutos) {
                if (produto.getNome().contains(nome))  {
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
            if (produto.getTipoProduto() == ProdutoEnum.INGREDIENTE){
                produtosNomes.add(produto.getNome());
            }
        }
        return produtosNomes.toArray();
    }


    }


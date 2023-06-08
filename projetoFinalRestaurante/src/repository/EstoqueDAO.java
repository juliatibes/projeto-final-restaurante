package repository;

import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<ProdutoEstoque> listaProdutosEstoque = new ArrayList<>();

    public static void salvarProdutoEstoque(Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedidaEnum) {
        Double quantidadeAtual = 0.00;
        ProdutoEstoque produtoEstoqueEncontrado = null;
        for (ProdutoEstoque produtoEstoque : listaProdutosEstoque) {
            if (produtoEstoque.getProduto().getId() == produto.getId()) {
                quantidadeAtual = produtoEstoque.getQuantidade();
                produtoEstoqueEncontrado = produtoEstoque;

            }
        }

        if (produtoEstoqueEncontrado != null) {
            listaProdutosEstoque.remove(produtoEstoqueEncontrado);
        }

        listaProdutosEstoque.add(new ProdutoEstoque(produto, quantidade + quantidadeAtual, unidadeMedidaEnum));
        System.out.println(listaProdutosEstoque);
    }

    public static Integer verificaDisponibilidade (Receita receita,Integer quantidade){

        Double quantidadeCalculada = 0.00;
        for (int x = 0; x < receita.getListaIngredientes().size(); x++) {

          quantidadeCalculada = receita.getListaIngredientes().get(x).getQuantidade()* quantidade;

          for (int y = 0; y < listaProdutosEstoque.size();y++){
              if (listaProdutosEstoque.get(x).getProduto().equals(receita.getListaIngredientes().get(x).getProduto())){
                  if (listaProdutosEstoque.get(x).getQuantidade() < quantidadeCalculada){
                  return JOptionPane.showConfirmDialog(null, "Nao tem estoque suficiente! ",
                          "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
                  }
              }
          }
        }
        return null;
    }
}

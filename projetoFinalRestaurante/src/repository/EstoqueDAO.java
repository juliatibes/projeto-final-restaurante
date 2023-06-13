package repository;

import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<ProdutoEstoque> listaProdutosEstoque = new ArrayList<>();

    public static void inputEstoque() {
        if (EstoqueDAO.listaProdutosEstoque.isEmpty()) {
            listaProdutosEstoque.add(new ProdutoEstoque(ProdutoDAO.listaProdutos.get(0),500.0, UnidadeMedidaEnum.GRAMA));
            listaProdutosEstoque.add(new ProdutoEstoque(ProdutoDAO.listaProdutos.get(1), 500.0, UnidadeMedidaEnum.GRAMA));
            listaProdutosEstoque.add(new ProdutoEstoque(ProdutoDAO.listaProdutos.get(2), 5.0, UnidadeMedidaEnum.UNIDADE));
            listaProdutosEstoque.add(new ProdutoEstoque(ProdutoDAO.listaProdutos.get(3), 200.0, UnidadeMedidaEnum.GRAMA));
        }
    }

    public static List<ProdutoEstoque> buscaTodos() {
            return listaProdutosEstoque;
    }

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
    }

    public static Integer verificaDisponibilidade (List<VendaPedido> listaItensCarinho){
        Double quantidadeCalculadaReceita = 0.00;
        List<ProdutoEstoque> listaProdutosEstoqueVerificacao = new ArrayList<>();


        for (ProdutoEstoque produtoEstoque : listaProdutosEstoque){
             listaProdutosEstoqueVerificacao.add(produtoEstoque);
        }


        for ( int z = 0; z < listaItensCarinho.size();z ++) {
            List<ProdutoEstoque> guardaProdutoEstoque = new ArrayList<>();

            if (listaItensCarinho.get(z).getProdutoBebida() != null) {
                for (int y = 0; y < listaProdutosEstoqueVerificacao.size(); y++) {

                    if (listaProdutosEstoqueVerificacao.get(y).getProduto().equals(listaItensCarinho.get(z).getProdutoBebida())) {
                        if (listaProdutosEstoqueVerificacao.get(y).getQuantidade() < listaItensCarinho.get(z).getQuantidade()) {
                            return 1;
                        } else {
                            guardaProdutoEstoque.add(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.remove(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.add(new ProdutoEstoque(guardaProdutoEstoque.get(0).getProduto(),
                                    guardaProdutoEstoque.get(0).getQuantidade() - listaItensCarinho.get(z).getQuantidade(),
                                    guardaProdutoEstoque.get(0).getUnidadeMedida()));
                            guardaProdutoEstoque.clear();
                            y = listaProdutosEstoqueVerificacao.size() -1;
                        }
                    }
                }
            }

            if (listaItensCarinho.get(z).getReceita() != null) {
              for (int x = 0; x < listaItensCarinho.get(z).getReceita().getListaIngredientes().size(); x++) {

                  quantidadeCalculadaReceita = listaItensCarinho.get(z).getReceita().getListaIngredientes().get(x).getQuantidade() *
                          listaItensCarinho.get(z).getQuantidade();

                  for (int y = 0; y < listaProdutosEstoqueVerificacao.size(); y++) {

                      if (listaProdutosEstoqueVerificacao.get(y).getProduto().equals
                              (listaItensCarinho.get(z).getReceita().getListaIngredientes().get(x).getProduto())) {
                          if (listaProdutosEstoqueVerificacao.get(y).getQuantidade() < quantidadeCalculadaReceita) {
                              return 1;
                          } else {
                              guardaProdutoEstoque.add(listaProdutosEstoqueVerificacao.get(y));

                              listaProdutosEstoqueVerificacao.remove(listaProdutosEstoqueVerificacao.get(y));
                              listaProdutosEstoqueVerificacao.add(new ProdutoEstoque(guardaProdutoEstoque.get(0).getProduto(),
                                      guardaProdutoEstoque.get(0).getQuantidade() - quantidadeCalculadaReceita,
                                      guardaProdutoEstoque.get(0).getUnidadeMedida()));
                              guardaProdutoEstoque.clear();
                              y = listaProdutosEstoqueVerificacao.size() -1;
                          }
                      }
                  }
              }
          }
        }
        return 0;
    }

    public static Integer baixaVendaEstoque (Venda venda){
        Double quantidadeCalculadaReceita = 0.00;

        for (int x = 0 ; x < venda.getListaVendaPedido().size() ; x ++) {

            if (venda.getListaVendaPedido().get(x).getProdutoBebida() != null) {
                for (int y = 0; y < listaProdutosEstoque.size(); y++) {
                    if (listaProdutosEstoque.get(y).getProduto().equals(venda.getListaVendaPedido().get(x).getProdutoBebida())){
                        listaProdutosEstoque.get(y).setQuantidade
                                (listaProdutosEstoque.get(y).getQuantidade() - venda.getListaVendaPedido().get(x).getQuantidade());

                    }
                }
            }

            if (venda.getListaVendaPedido().get(x).getReceita() != null) {
                for (int z = 0; z < venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().size(); z++) {

                    quantidadeCalculadaReceita = venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().get(z).getQuantidade() *
                            venda.getListaVendaPedido().get(x).getQuantidade();

                    for (int y = 0; y < listaProdutosEstoque.size(); y++) {
                        if (listaProdutosEstoque.get(y).getProduto().equals
                                (venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().get(z).getProduto())) {

                            listaProdutosEstoque.get(y).setQuantidade
                                    (listaProdutosEstoque.get(y).getQuantidade() - quantidadeCalculadaReceita);
                        }
                    }
                }
            }
        }

        return JOptionPane.showConfirmDialog(null, "Venda concluida com sucesso!",
                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }
}

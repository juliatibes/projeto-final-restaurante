package repository;

import model.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BebidaOfertaDoDIaDAO implements InterfaceAutoIncrement {

    static List<BebidaOfertaDia> listaBebidaOfertaDia =  new ArrayList<>();

    public static List<BebidaOfertaDia> buscarTodos() {
        return listaBebidaOfertaDia;
    }

    public static void salvarOfertaBebida(BebidaOfertaDia bebidaOfertaDia){
        setDescontoBebida(bebidaOfertaDia);

        BebidaOfertaDoDIaDAO bebidaOfertaDoDIaDAO = new BebidaOfertaDoDIaDAO();
        bebidaOfertaDia.setId(bebidaOfertaDoDIaDAO.geraID());
        listaBebidaOfertaDia.add(bebidaOfertaDia);
    }

    public static void setDescontoBebida (BebidaOfertaDia bebidaOfertaDia){
        for (Produto produto : ProdutoDAO.listaProdutos) {
            if (produto.getTipoProduto().equals(ProdutoEnum.BEBIDA)) {
                if (bebidaOfertaDia.getProduto().equals(produto)) {
                    produto.setValorVendaProduto(produto.getValorVendaProduto().subtract(produto.getValorVendaProduto().multiply(
                            BigDecimal.valueOf(bebidaOfertaDia.getDesconto()).divide(BigDecimal.valueOf(100)))));
                }
            }
        }
    }

    public static List<BebidaOfertaDia> buscarPorNomeBebidaOferta(String nome) {
        List<BebidaOfertaDia> bebidasOfertaFiltradas = new ArrayList<>();
        for (BebidaOfertaDia bebidaOfertaDia : listaBebidaOfertaDia) {
            if (bebidaOfertaDia.getProduto().getNome().contains(nome)) {
                bebidasOfertaFiltradas.add(bebidaOfertaDia);
            }
        }
        return bebidasOfertaFiltradas;
    }

    public static Object[] findreceitasInArrayBebidaOferta() {
        List<BebidaOfertaDia> bebidaOfertaDia = buscarTodos();
        List<String> bebidaOfertaNomes = new ArrayList<>();

        for (BebidaOfertaDia bebidaOfertaDia1 : bebidaOfertaDia) {
            bebidaOfertaNomes.add(bebidaOfertaDia1.getProduto().getNome());
        }
        return bebidaOfertaNomes.toArray();
    }

    public static Integer removerOfertaBebida(BebidaOfertaDia ofertaBebida) {
        removerDescontoBebida(ofertaBebida);

        listaBebidaOfertaDia.remove(ofertaBebida);
        return JOptionPane.showConfirmDialog(null, "Oferta excluída com sucesso!",
                "Remover Oferta", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static void removerDescontoBebida (BebidaOfertaDia bebidaOfertaDia){
        for (Produto produto : ProdutoDAO.listaProdutos) {
            if (produto.getTipoProduto().equals(ProdutoEnum.BEBIDA)) {
                if (bebidaOfertaDia.getProduto().equals(produto)) {
                    produto.setValorVendaProduto(produto.getValorCustoProduto().add(produto.getValorCustoProduto().multiply(
                            BigDecimal.valueOf(100).divide(BigDecimal.valueOf(100)))));
                }
            }
        }
    }

    @Override
    public Integer geraID() {
        Integer id = listaBebidaOfertaDia.size() + 1;
        return id;
    }
}

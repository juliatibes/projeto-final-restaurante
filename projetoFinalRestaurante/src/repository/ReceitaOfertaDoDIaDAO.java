package repository;

import model.BebidaOfertaDia;
import model.InterfaceAutoIncrement;
import model.Receita;
import model.ReceitaOfertaDia;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceitaOfertaDoDIaDAO implements InterfaceAutoIncrement {

    static List<ReceitaOfertaDia> listaReceitaOfertaDia =  new ArrayList<>();

    public static List<ReceitaOfertaDia> buscarTodos() {
        return listaReceitaOfertaDia;
    }

    public static void salvarOfertaReceita(ReceitaOfertaDia receitaOfertaDia){
        setDescontoReceita(receitaOfertaDia);

        ReceitaOfertaDoDIaDAO receitaOfertaDoDIaDAO = new ReceitaOfertaDoDIaDAO();
        receitaOfertaDia.setId(receitaOfertaDoDIaDAO.geraID());
        listaReceitaOfertaDia.add(receitaOfertaDia);
    }

    public static void setDescontoReceita (ReceitaOfertaDia receitaOfertaDia){
        for (Receita receita : ReceitaDAO.listaReceita){
            if (receitaOfertaDia.getReceita().equals(receita)){
                receita.setValorVenda(receita.getValorVenda().subtract(receita.getValorVenda().multiply(
                        BigDecimal.valueOf(receitaOfertaDia.getDesconto()).divide(BigDecimal.valueOf(100)))));
            }
        }
    }


    public static List<ReceitaOfertaDia> buscarPorNomeReceitaOferta(String nome) {
        List<ReceitaOfertaDia> receitasOfertaFiltradasReceita = new ArrayList<>();
        for (ReceitaOfertaDia receitaOfertaDia : listaReceitaOfertaDia) {
            if (receitaOfertaDia.getReceita().getNome().contains(nome)) {
                receitasOfertaFiltradasReceita.add(receitaOfertaDia);
            }
        }
        return receitasOfertaFiltradasReceita;
    }

    public static Object[] findreceitasInArrayReceitaOferta() {
        List<ReceitaOfertaDia> receitaOfertaDia = buscarTodos();
        List<String> receitaOfertaNomes = new ArrayList<>();

        for (ReceitaOfertaDia receitaOfertaDia1 : receitaOfertaDia) {
            receitaOfertaNomes.add(receitaOfertaDia1.getReceita().getNome());
        }
        return receitaOfertaNomes.toArray();
    }

    public static Integer removerOfertaReceita(ReceitaOfertaDia ofertaReceita) {
        removerDescontoReceita(ofertaReceita);

        listaReceitaOfertaDia.remove(ofertaReceita);
        return JOptionPane.showConfirmDialog(null, "Oferta excluida com sucesso!",
                "Remover Oferta", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static void removerDescontoReceita (ReceitaOfertaDia receitaOfertaDia){
        for (Receita receita : ReceitaDAO.listaReceita){
            if (receitaOfertaDia.getReceita().equals(receita)){
                receita.setValorVenda(receita.getValorCusto().add(receita.getValorCusto().multiply(
                        BigDecimal.valueOf(100).divide(BigDecimal.valueOf(100)))));
            }
        }
    }

    @Override
    public Integer geraID() {
        Integer id = listaReceitaOfertaDia.size() + 1;
        return id;
    }
}

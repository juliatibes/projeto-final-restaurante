package repository;

import model.Compra;
import model.FormaPagamentoEnum;
import model.Venda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {
    static List<Venda> listaVenda = new ArrayList<>();

    public static void inputVendas() {
        if (VendaDAO.listaVenda.isEmpty()) {
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(1), FormaPagamentoEnum.PIX));
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(1), FormaPagamentoEnum.CREDITO));
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(2), FormaPagamentoEnum.CREDITO));
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(2), FormaPagamentoEnum.DINHEIRO));
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(3), FormaPagamentoEnum.DEBITO));
            listaVenda.add(new Venda(ReceitaDAO.listaReceita.get(3), FormaPagamentoEnum.PIX));
        }
    }

    public static void salvarNovaVenda(Venda venda) {
        listaVenda.add(venda);
    }

    public static List<Venda> buscarTodos() {
        inputVendas();
        return listaVenda;
    }


}

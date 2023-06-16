package repository;

import model.InterfaceAutoIncrement;
import model.PlanejamentoProducao;
import model.Receita;
import model.UnidadeMedidaEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanejamentoProducaoDAO implements InterfaceAutoIncrement {

    static List<PlanejamentoProducao> listaPlaneja = new ArrayList<>();

    public static void salvarPlanejamento(PlanejamentoProducao ofertaSelecionada) {
        PlanejamentoProducaoDAO planejamentoProducaoDAO = new PlanejamentoProducaoDAO();
        ofertaSelecionada.setId(planejamentoProducaoDAO.geraID());
        listaPlaneja.add(ofertaSelecionada);
    }

    @Override
    public Integer geraID() {
        Integer id = listaPlaneja.size() + 1;
        return id;
    }
}

package repository;

import model.PlanejamentoProducao;
import model.Receita;
import model.UnidadeMedidaEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanejamentoProducaoDAO {

    static List<PlanejamentoProducao> listaPlaneja = new ArrayList<PlanejamentoProducao>();

    public PlanejamentoProducaoDAO() {
    }


    public static void inputPlanejamento(){
        if (PlanejamentoProducaoDAO.listaPlaneja.isEmpty()) {
            listaPlaneja.add(new PlanejamentoProducao(1, ReceitaDAO.listaReceita.get(0), 1000.00, UnidadeMedidaEnum.GRAMA, LocalDate.now()));
        }
    }

    public static boolean salvarPlanejamento(PlanejamentoProducao receita)
    {
        return listaPlaneja.add(receita);
    }
    public static List<PlanejamentoProducao> planeja() { return listaPlaneja;}

    public static Integer aiID(){
        Integer id = listaPlaneja.size() + 1;
        return id;
    }

}

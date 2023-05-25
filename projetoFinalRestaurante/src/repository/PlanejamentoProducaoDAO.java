package repository;

import model.PlanejamentoProducao;
import model.Receita;
import model.UnidadeMedidaEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlanejamentoProducaoDAO {

    private Integer id;
    private LocalDate diaPlanejado;
    private Receita receita;
    private Double quantidadePlanejada;
    private UnidadeMedidaEnum unidadeMedida;

    public PlanejamentoProducaoDAO(Integer id, LocalDate diaPlanejado, Receita receita,
                                   Double quantidadePlanejada, UnidadeMedidaEnum unidadeMedida) {
        this.id = id;
        this.diaPlanejado = diaPlanejado;
        this.receita = receita;
        this.quantidadePlanejada = quantidadePlanejada;
        this.unidadeMedida = unidadeMedida;
    }


    public static void inputirdados(){
        if (PlanejamentoProducaoDAO.listaPlaneja.isEmpty()) {
            listaPlaneja.add(new PlanejamentoProducao(1, ReceitaDAO.listaReceita.get(0), 1000.00, UnidadeMedidaEnum.GRAMA, LocalDate.now()));
        }
    }


    static List<PlanejamentoProducao> listaPlaneja = new ArrayList<PlanejamentoProducao>();
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

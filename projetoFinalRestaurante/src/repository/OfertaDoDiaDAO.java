package repository;

import model.OfertaDia;
import model.Receita;
import model.ReceitaOfertaDia;

import java.util.ArrayList;
import java.util.List;

public class OfertaDoDiaDAO {

    static List <OfertaDia> listaOfertas = new ArrayList<>();

    public static void salvarOfertaReceita(OfertaDia ofertaDia){
        listaOfertas.add(ofertaDia);
    }


}

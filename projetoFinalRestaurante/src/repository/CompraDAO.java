package repository;

import model.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    static List<Compra> listaCompras = new ArrayList<>();
    static List<Compra> listaComprasRelatorio = new ArrayList<>();

    public static void AdicionarNovaCompra(Compra produtos) {
        listaCompras.add(produtos);
        listaComprasRelatorio.add(produtos); // passivel de alteração para apenas uma lista ( comparar datas)
    }

    public static void Cancelar(){}

    public static void FinalizarCompra(){

    }
}

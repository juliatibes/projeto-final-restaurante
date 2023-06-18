package relatorio;

import model.Produto;
import model.ProdutoEnum;
import repository.ProdutoDAO;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioBebida extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_VALORCUSTO = 1;
    public static final int INDEX_VALORVENDA = 2;
    public static final int INDEX_LUCRO = 3;
    public static final int INDEX_ESCONDIDO = 4;

    protected String[] nomeColunas;
    protected Vector<Produto> vetorDados;

    public RelatorioBebida(String[] columnNames, Vector<Produto> vetorDados) {
        this.nomeColunas = columnNames;
        this.vetorDados = vetorDados;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == INDEX_ESCONDIDO) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto registroBebidas = (Produto) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroBebidas.getNome();
            case INDEX_VALORCUSTO:
                return registroBebidas.getValorCustoProduto();
            case INDEX_VALORVENDA:
                return registroBebidas.getValorVendaProduto();
            case INDEX_LUCRO:
                return registroBebidas;
            default:
                return new Object();
        }
    }

    @Override
    public int getRowCount() {
        return vetorDados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }
}

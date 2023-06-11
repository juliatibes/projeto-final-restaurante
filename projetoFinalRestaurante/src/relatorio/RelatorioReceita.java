package relatorio;

import model.Receita;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioReceita extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_VALORCUSTO = 1;
    public static final int INDEX_VALORVENDA = 2;
    public static final int INDEX_LUCRO = 3;
    public static final int INDEX_ESCONDIDO = 4;

    protected String[] nomeColunas;
    protected Vector<Receita> vetorDados;

    public RelatorioReceita(String[] columnNames, Vector<Receita> vetorDados) {
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
        Receita registroReceita = (Receita) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroReceita.getNome();
            case INDEX_VALORCUSTO:
                return registroReceita.getValorCusto();
            case INDEX_VALORVENDA:
                return registroReceita.getValorVenda();
            case INDEX_LUCRO:
                return registroReceita.calculaLucro();
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

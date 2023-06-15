package relatorio;

import model.Venda;
import repository.VendaDAO;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioVenda extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_DATAVENDA = 0;
    public static final int INDEX_QUANTIDADE = 1;
    public static final int INDEX_VALOR = 2;
    public static final int INDEX_LUCRO = 3;
    public static final int INDEX_ESCONDIDO = 4;

    protected String[] nomeColunas;
    protected Vector<Venda> vetorDados;

    public RelatorioVenda(String[] columnNames, Vector<Venda> vetorDados) {
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
        Venda registroVenda = (Venda) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_DATAVENDA:
                return registroVenda.getDataVenda();
            case INDEX_QUANTIDADE:
                return VendaDAO.totalQuantidadeItens();
            case INDEX_VALOR:
                return VendaDAO.totalVendas();
            case INDEX_LUCRO:
                return VendaDAO.totalLucro();
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

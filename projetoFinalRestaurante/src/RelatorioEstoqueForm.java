import model.ProdutoEstoque;
import relatorio.RelatorioEstoque;

import javax.swing.*;
import javax.swing.table.TableColumn;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioEstoqueForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Produto", "Quantidade", "Unidade de Medida", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioEstoque tabela;

    public RelatorioEstoqueForm(Vector<ProdutoEstoque> vetorDados) {
        iniciarComponentes(vetorDados);
    }

    public void iniciarComponentes(Vector<ProdutoEstoque> vetorDados) {
        tabela = new RelatorioEstoque(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioEstoque.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);


    }

    public static void emitirRelatorio(List<ProdutoEstoque> produtos) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatorio de itens em Estoque");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    Main.chamaMenuRelatorios();
                }
            });
            Vector<ProdutoEstoque> vetorDados = new Vector<ProdutoEstoque>();
            for (ProdutoEstoque produto : produtos) {
                vetorDados.add(produto);
            }

            frame.getContentPane().add(new RelatorioEstoqueForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


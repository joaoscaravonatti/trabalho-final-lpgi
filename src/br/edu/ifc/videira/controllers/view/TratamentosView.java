package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifc.videira.DAOs.TratamentoDao;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JFormattedTextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.awt.event.WindowFocusListener;

public class TratamentosView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfPesquisar;
	private List<Object> tratamentos = new ArrayList<Object>();
	private TratamentoDao trDao = new TratamentoDao();
	private JButton btnNewButton;
	private JTextField tfIdTratamento;
	JComboBox cbFiltro = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TratamentosView frame = new TratamentosView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TratamentosView() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				atualizarTabela();
			}

			public void windowLostFocus(WindowEvent e) {
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizarTabela();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TratamentosView.class.getResource("/br/edu/ifc/videira/imgs/500x500.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 442);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 10, 701, 197);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				setCampos();
			}
		});
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Tratamento", "Paciente", "\u00DAltimo procedimento", "Data", "Status" }));
		scrollPane.setViewportView(table);

		JLabel label = new JLabel("");
		label.setBounds(12, 12, 274, 153);
		label.setIcon(new ImageIcon(TratamentosView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		contentPane.add(label);

		JLabel lblPesquisarPorNome = new JLabel("Pesquisar por nome");
		lblPesquisarPorNome.setBounds(294, 219, 138, 19);
		lblPesquisarPorNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblPesquisarPorNome);

		tfPesquisar = new JTextField();
		tfPesquisar.setBounds(450, 218, 261, 20);
		tfPesquisar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);

				if (tfPesquisar.getText().length() == 0) {
					filtro.setRowFilter(null);
				} else {
					filtro.setRowFilter(RowFilter.regexFilter("(?i)" + tfPesquisar.getText(), 1));
				}
			}
		});
		tfPesquisar.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(tfPesquisar);
		tfPesquisar.setColumns(10);

		btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(415, 348, 174, 27);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		contentPane.add(btnNewButton);

		JLabel lblIdTratatamento = new JLabel("ID Tratatamento");
		lblIdTratatamento.setBounds(294, 267, 121, 19);
		lblIdTratatamento.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(lblIdTratatamento);

		tfIdTratamento = new JTextField();
		tfIdTratamento.setEditable(false);
		tfIdTratamento.setBounds(450, 266, 63, 20);
		tfIdTratamento.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfIdTratamento.setColumns(10);
		contentPane.add(tfIdTratamento);

		JButton btnVerProcedimentos = new JButton("Ver procedimentos");
		btnVerProcedimentos.setBounds(537, 263, 174, 27);
		btnVerProcedimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIdTratamento.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um tratamento");
				} else {
					TratamentoView tv = new TratamentoView();
					tv.setLocationRelativeTo(null);
					tv.setVisible(true);
					TratamentoView.tfIdTratamento.setText(tfIdTratamento.getText());
				}
			}
		});
		btnVerProcedimentos.setFont(new Font("Dialog", Font.PLAIN, 14));
		contentPane.add(btnVerProcedimentos);

		cbFiltro.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				TableRowSorter<TableModel> filtro = null;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				filtro = new TableRowSorter<TableModel>(model);
				table.setRowSorter(filtro);

				if (cbFiltro.getSelectedIndex() == 0) {
					filtro.setRowFilter(null);
				} else if (cbFiltro.getSelectedIndex() > 0) {
					filtro.setRowFilter(RowFilter.regexFilter("" + cbFiltro.getSelectedItem(), 4));
				}
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

			}
		});
		cbFiltro.setModel(new DefaultComboBoxModel(new String[] { "Todos", "Andamento", "Finalizado" }));
		cbFiltro.setFont(new Font("Dialog", Font.PLAIN, 14));
		cbFiltro.setBounds(808, 215, 186, 26);
		contentPane.add(cbFiltro);

		JLabel lblFiltrar = new JLabel("Filtrar");
		lblFiltrar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblFiltrar.setBounds(739, 219, 77, 19);
		contentPane.add(lblFiltrar);
	}

	public void atualizarTabela() {
		try {
			tratamentos = trDao.buscarUltimosTratamento();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != tratamentos.size(); x++) {
				model.addRow((Object[]) tratamentos.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setCampos() {
		tfIdTratamento.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	}
}

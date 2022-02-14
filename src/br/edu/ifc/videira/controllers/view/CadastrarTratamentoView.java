package br.edu.ifc.videira.controllers.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifc.videira.DAOs.PacienteDAO;
import br.edu.ifc.videira.DAOs.ProcedimentoDao;
import br.edu.ifc.videira.DAOs.TratamentoDao;
import br.edu.ifc.videira.beans.Tratamento;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class CadastrarTratamentoView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField tfId;
	private JTextField tfPesquisar;
	private ProcedimentoDao pDao = new ProcedimentoDao();
	private PacienteDAO paDao = new PacienteDAO();
	private List<Object> procedimentos = new ArrayList<Object>();
	private List<Object> pacientes = new ArrayList<Object>();
	private Tratamento tr = new Tratamento();
	private TratamentoDao trDao = new TratamentoDao();
	JComboBox<Object> cbProcedimento = new JComboBox<Object>();
	private JTextField tfObservacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarTratamentoView frame = new CadastrarTratamentoView();
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
	public CadastrarTratamentoView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				atualizarTabela();
			}

			@Override
			public void windowActivated(WindowEvent e) {
				atualizarTabela();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CadastrarTratamentoView.class.getResource("/br/edu/ifc/videira/imgs/800x800.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(
				new ImageIcon(CadastrarTratamentoView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		label.setBounds(12, 12, 257, 157);
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(304, 12, 524, 227);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setCampos();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Data de nascimento", "Cidade" }));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JLabel lblIdDoPaciente = new JLabel("ID do paciente");
		lblIdDoPaciente.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIdDoPaciente.setBounds(29, 312, 130, 17);
		contentPane.add(lblIdDoPaciente);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfId.setBounds(156, 305, 114, 30);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JButton btnIniciarTratamento = new JButton("Iniciar tratamento");
		btnIniciarTratamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um paciente");
				} else {
					tr.setIdPaciente(Integer.parseInt(tfId.getText()));
					tr.setIdProcedimento(cbProcedimento.getSelectedIndex() + 1);
					tr.setObservacao(tfObservacao.getText());
					try {
						trDao.cadastrarTratamento(tr);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		btnIniciarTratamento.setFont(new Font("Dialog", Font.BOLD, 14));
		btnIniciarTratamento.setBounds(444, 492, 168, 30);
		contentPane.add(btnIniciarTratamento);

		cbProcedimento.setModel(new DefaultComboBoxModel<Object>());
		cbProcedimento.setFont(new Font("Dialog", Font.BOLD, 14));
		cbProcedimento.setBounds(156, 354, 239, 37);
		contentPane.add(cbProcedimento);

		JLabel lblProcedimento = new JLabel("Procedimento");
		lblProcedimento.setFont(new Font("Dialog", Font.BOLD, 14));
		lblProcedimento.setBounds(29, 364, 130, 17);
		contentPane.add(lblProcedimento);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPesquisar.setBounds(304, 252, 100, 17);
		contentPane.add(lblPesquisar);

		tfPesquisar = new JTextField();
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
		tfPesquisar.setColumns(10);
		tfPesquisar.setBounds(390, 250, 369, 30);
		contentPane.add(tfPesquisar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(264, 492, 168, 30);
		contentPane.add(btnVoltar);

		JLabel lblObservao = new JLabel("Observa\u00E7\u00E3o");
		lblObservao.setFont(new Font("Dialog", Font.BOLD, 14));
		lblObservao.setBounds(29, 410, 130, 17);
		contentPane.add(lblObservao);

		tfObservacao = new JTextField();
		tfObservacao.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfObservacao.setColumns(10);
		tfObservacao.setBounds(156, 403, 350, 30);
		contentPane.add(tfObservacao);

		try {
			procedimentos = pDao.buscar();
			for (int i = 0; i < procedimentos.size(); i++) {
				cbProcedimento.addItem(procedimentos.get(i));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void atualizarTabela() {
		try {
			pacientes = paDao.buscar();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != pacientes.size(); x++) {
				model.addRow((Object[]) pacientes.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setCampos() {
		tfId.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	}
}

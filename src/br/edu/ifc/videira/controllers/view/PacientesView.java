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
import javax.swing.JTextPane;
import javax.swing.RowFilter;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.edu.ifc.videira.DAOs.PacienteDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PacientesView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfPesquisar;
	private List<Object> pacientes = new ArrayList<Object>();
	private PacienteDAO paDao = new PacienteDAO();
	private JTextField tfIdPaciente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacientesView frame = new PacientesView();
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
	public PacientesView() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PacientesView.class.getResource("/br/edu/ifc/videira/imgs/500x500.png")));
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				atualizarTabela();
			}

			public void windowLostFocus(WindowEvent e) {
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				atualizarTabela();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 945, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(12, 0, 285, 171);
		label.setIcon(new ImageIcon(PacientesView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		contentPane.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 12, 605, 159);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setCampos();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Data de nascimento", "Cidade", "E-mail" }));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(396, 335, 151, 27);
		contentPane.add(btnVoltar);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPesquisar.setBounds(315, 183, 93, 17);
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
		tfPesquisar.setBounds(396, 183, 266, 21);
		contentPane.add(tfPesquisar);
		tfPesquisar.setColumns(10);

		tfIdPaciente = new JTextField();
		tfIdPaciente.setEditable(false);
		tfIdPaciente.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfIdPaciente.setColumns(10);
		tfIdPaciente.setBounds(406, 260, 51, 21);
		contentPane.add(tfIdPaciente);

		JLabel lblIdPaciente = new JLabel("ID Paciente");
		lblIdPaciente.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIdPaciente.setBounds(315, 263, 93, 17);
		contentPane.add(lblIdPaciente);

		JButton btnVerPaciente = new JButton("Ver paciente");
		btnVerPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIdPaciente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um paciente");
				} else {
					PacienteView pv = new PacienteView();
					PacienteView.tfId.setText(tfIdPaciente.getText());
					pv.setLocationRelativeTo(null);
					pv.setVisible(true);
				}
			}
		});
		btnVerPaciente.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnVerPaciente.setBounds(486, 258, 130, 27);
		contentPane.add(btnVerPaciente);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfIdPaciente.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um paciente");
				} else {
					int decisao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?");
					if (decisao == JOptionPane.YES_OPTION) {
						try {
							paDao.excluirPaciente(Integer.parseInt(tfIdPaciente.getText()));
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnExcluir.setBounds(628, 258, 130, 27);
		contentPane.add(btnExcluir);
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
		tfIdPaciente.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
	}
}

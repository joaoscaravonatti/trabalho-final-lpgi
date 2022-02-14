package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ifc.videira.DAOs.PacienteDAO;
import br.edu.ifc.videira.beans.Paciente;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PacienteView extends JFrame {

	private JPanel contentPane;
	public static JTextField tfId;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfCidade;
	private JTextField tfCep;
	private JTextField tfSexo;
	private JTextField tfTelefone;
	private JTextField tfDataNascimento;
	private JTextField tfEstadoCivil;
	private JTextField tfProfissao;
	private JTextField tfEmail;
	private JTextField tfIndicacao;
	private JTextField tfEsporte;
	private Map<String, String> paciente = new HashMap<String, String>();
	private PacienteDAO paDao = new PacienteDAO();
	private Paciente pa = new Paciente();
	private JTextField tfBairro;
	private JTextField tfCalcado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacienteView frame = new PacienteView();
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
	public PacienteView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				setCampos();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PacienteView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		label.setBounds(10, 11, 278, 163);
		contentPane.add(label);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblId.setBounds(340, 11, 26, 19);
		contentPane.add(lblId);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfId.setBounds(412, 11, 44, 19);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblNome.setBounds(340, 41, 52, 19);
		contentPane.add(lblNome);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNome.setColumns(10);
		tfNome.setBounds(412, 41, 232, 19);
		contentPane.add(tfNome);

		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEndereco.setBounds(340, 101, 67, 19);
		contentPane.add(lblEndereco);

		tfEndereco = new JTextField();
		tfEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(412, 101, 232, 19);
		contentPane.add(tfEndereco);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCidade.setBounds(340, 161, 67, 19);
		contentPane.add(lblCidade);

		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCidade.setColumns(10);
		tfCidade.setBounds(412, 161, 232, 19);
		contentPane.add(tfCidade);

		tfCep = new JTextField();
		tfCep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCep.setColumns(10);
		tfCep.setBounds(412, 191, 232, 19);
		contentPane.add(tfCep);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCep.setBounds(340, 191, 67, 19);
		contentPane.add(lblCep);

		tfSexo = new JTextField();
		tfSexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfSexo.setColumns(10);
		tfSexo.setBounds(412, 71, 232, 19);
		contentPane.add(tfSexo);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblSexo.setBounds(340, 71, 67, 19);
		contentPane.add(lblSexo);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTelefone.setBounds(340, 221, 67, 19);
		contentPane.add(lblTelefone);

		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(412, 221, 232, 19);
		contentPane.add(tfTelefone);

		JLabel lblDataDeNascimento = new JLabel("Data de nascimento");
		lblDataDeNascimento.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(340, 251, 138, 19);
		contentPane.add(lblDataDeNascimento);

		tfDataNascimento = new JTextField();
		tfDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfDataNascimento.setColumns(10);
		tfDataNascimento.setBounds(488, 251, 156, 19);
		contentPane.add(tfDataNascimento);

		JLabel lblEstadoCivil = new JLabel("Estado civil");
		lblEstadoCivil.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstadoCivil.setBounds(340, 281, 92, 19);
		contentPane.add(lblEstadoCivil);

		tfEstadoCivil = new JTextField();
		tfEstadoCivil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfEstadoCivil.setColumns(10);
		tfEstadoCivil.setBounds(430, 281, 214, 19);
		contentPane.add(tfEstadoCivil);

		JLabel lblProfisso = new JLabel("Profiss\u00E3o");
		lblProfisso.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblProfisso.setBounds(340, 311, 67, 19);
		contentPane.add(lblProfisso);

		tfProfissao = new JTextField();
		tfProfissao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfProfissao.setColumns(10);
		tfProfissao.setBounds(412, 311, 232, 19);
		contentPane.add(tfProfissao);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEmail.setBounds(340, 341, 67, 19);
		contentPane.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfEmail.setColumns(10);
		tfEmail.setBounds(412, 341, 232, 19);
		contentPane.add(tfEmail);

		JLabel lblIndicao = new JLabel("Indica\u00E7\u00E3o");
		lblIndicao.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIndicao.setBounds(340, 371, 67, 19);
		contentPane.add(lblIndicao);

		tfIndicacao = new JTextField();
		tfIndicacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIndicacao.setColumns(10);
		tfIndicacao.setBounds(412, 371, 232, 19);
		contentPane.add(tfIndicacao);

		JLabel lblEsporte = new JLabel("Esporte");
		lblEsporte.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEsporte.setBounds(340, 401, 67, 19);
		contentPane.add(lblEsporte);

		tfEsporte = new JTextField();
		tfEsporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfEsporte.setColumns(10);
		tfEsporte.setBounds(412, 401, 232, 19);
		contentPane.add(tfEsporte);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(198, 478, 128, 27);
		contentPane.add(btnVoltar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pa.setNome(tfNome.getText() + "");
				pa.setBairro(tfBairro.getText() + "");
				pa.setCalcado(tfCalcado.getText() + "");
				pa.setCep(tfCep.getText() + "");
				pa.setCidade(tfCidade.getText() + "");
				pa.setEmail(tfEmail.getText() + "");
				String[] data = tfDataNascimento.getText().split("-");
				String dataFormatada = data[2] + "-" + data[1] + "-" + data[0];
				pa.setDataNascimento(dataFormatada);
				pa.setEndereco(tfEndereco.getText() + "");
				pa.setProfissao(tfProfissao.getText() + "");
				pa.setEstadoCivil(tfEstadoCivil.getText() + "");
				pa.setId(Integer.parseInt(tfId.getText()));
				pa.setIndicacao(tfIndicacao.getText() + "");
				pa.setSexo(tfSexo.getText() + "");
				pa.setTelefone(tfTelefone.getText() + "");
				try {
					paDao.alterarPaciente(pa);
					setCampos();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnEditar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEditar.setBounds(343, 478, 113, 27);
		contentPane.add(btnEditar);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblBairro.setBounds(340, 131, 67, 19);
		contentPane.add(lblBairro);

		tfBairro = new JTextField();
		tfBairro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfBairro.setColumns(10);
		tfBairro.setBounds(412, 131, 232, 19);
		contentPane.add(tfBairro);

		JLabel lblCalado = new JLabel("Cal\u00E7ado");
		lblCalado.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblCalado.setBounds(340, 431, 67, 19);
		contentPane.add(lblCalado);

		tfCalcado = new JTextField();
		tfCalcado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCalcado.setColumns(10);
		tfCalcado.setBounds(412, 431, 232, 19);
		contentPane.add(tfCalcado);

		JButton btnHistoriaCilnica = new JButton("Hist\u00F3ria cl\u00EDnica");
		btnHistoriaCilnica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoriaClinicaView hcv = new HistoriaClinicaView();
				hcv.setLocationRelativeTo(null);
				HistoriaClinicaView.tfId.setText(tfId.getText());
				hcv.setVisible(true);
			}
		});
		btnHistoriaCilnica.setFont(new Font("Dialog", Font.BOLD, 14));
		btnHistoriaCilnica.setBounds(83, 265, 169, 27);
		contentPane.add(btnHistoriaCilnica);

		JButton btnExameFisico = new JButton("Exame f\u00EDsico");
		btnExameFisico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExameFisicoView efv = new ExameFisicoView();
				ExameFisicoView.tfId.setText(tfId.getText());
				efv.setLocationRelativeTo(null);
				efv.setVisible(true);
			}
		});
		btnExameFisico.setFont(new Font("Dialog", Font.BOLD, 14));
		btnExameFisico.setBounds(83, 320, 169, 27);
		contentPane.add(btnExameFisico);
	}

	public void setCampos() {
		try {
			paciente = paDao.buscarPaciente(Integer.parseInt(tfId.getText()));
			tfNome.setText(paciente.get("nome"));
			tfSexo.setText(paciente.get("sexo"));
			tfEndereco.setText(paciente.get("endereco"));
			tfCidade.setText(paciente.get("cidade"));
			tfCep.setText(paciente.get("cep"));
			tfTelefone.setText(paciente.get("telefone"));
			tfDataNascimento.setText(paciente.get("dataNascimento"));
			tfEstadoCivil.setText(paciente.get("estadoCivil"));
			tfProfissao.setText(paciente.get("profissao"));
			tfEmail.setText(paciente.get("email"));
			tfIndicacao.setText(paciente.get("indicacao"));
			tfEsporte.setText(paciente.get("esporte"));
			tfBairro.setText(paciente.get("bairro"));
			tfCalcado.setText(paciente.get("calcado"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}

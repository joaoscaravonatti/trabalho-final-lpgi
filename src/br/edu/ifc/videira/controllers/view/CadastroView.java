package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.ifc.videira.DAOs.ExameFisicoDao;
import br.edu.ifc.videira.DAOs.HistoriaClinicaDao;
import br.edu.ifc.videira.DAOs.PacienteDAO;
import br.edu.ifc.videira.beans.ExameFisico;
import br.edu.ifc.videira.beans.HistoriaClinica;
import br.edu.ifc.videira.beans.Paciente;
import br.edu.ifc.videira.beans.Problema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class CadastroView extends JFrame {
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfCep;
	private JTextField tfProfissao;
	private JFormattedTextField tfDataNascimento;
	private JTextField tfEstadoCivil;
	private JTextField tfBairro;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfEsporte;
	private JTextField tfCalcado;
	private JTextField tfObs1;
	private JTextField tfObs2;
	private JTextField tfOutros;
	private JTextField tfMedicamento;
	private JTextField tfAlergia;
	private JTextField tfCirurgia;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private JTextField tfEmail;
	private JTextField tfTelefone;
	private JTextField tfCidade;
	JRadioButton rdSimMedicamento = new JRadioButton("Sim");
	JRadioButton rdNaoMedicamento = new JRadioButton("N\u00E3o");
	JRadioButton rdSimAlergia = new JRadioButton("Sim");
	JRadioButton rdNaoAlergia = new JRadioButton("N\u00E3o");
	JRadioButton rdMuita = new JRadioButton("Muita");
	JRadioButton rdPouca = new JRadioButton("Pouca");
	JRadioButton rdSuportavel = new JRadioButton("Suport\u00E1vel");
	JRadioButton rdNenhuma = new JRadioButton("Nenhuma");
	JRadioButton rdFunil = new JRadioButton("Funil");
	JRadioButton rdGancho = new JRadioButton("Gancho");
	JRadioButton rdTorques = new JRadioButton("Torques");
	JRadioButton rdCaracol = new JRadioButton("Caracol");
	Paciente p = new Paciente();
	PacienteDAO pDao = new PacienteDAO();
	Problema pr = new Problema();
	ExameFisico ef = new ExameFisico();
	ExameFisicoDao efDao = new ExameFisicoDao();
	HistoriaClinica hc = new HistoriaClinica();
	HistoriaClinicaDao hcDao = new HistoriaClinicaDao();
	ArrayList<Integer> problemasFisicos = new ArrayList<>();
	ArrayList<Integer> problemasClinicos = new ArrayList<>();
	private JTextField tfIndicacao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroView frame = new CadastroView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws ParseException
	 */
	public CadastroView() throws ParseException {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(CadastroView.class.getResource("/br/edu/ifc/videira/imgs/800x800.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ficha Podol\u00F3gica/Anamnese");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 11, 1029, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNome.setBounds(285, 47, 70, 14);
		contentPane.add(lblNome);

		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSexo.setBounds(285, 73, 70, 14);
		contentPane.add(lblSexo);

		JLabel lblEndereco = new JLabel("Endere\u00E7o");
		lblEndereco.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEndereco.setBounds(285, 101, 70, 14);
		contentPane.add(lblEndereco);

		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCep.setBounds(285, 126, 70, 14);
		contentPane.add(lblCep);

		JLabel lblProfisso = new JLabel("Profiss\u00E3o");
		lblProfisso.setFont(new Font("Arial", Font.PLAIN, 14));
		lblProfisso.setBounds(285, 151, 70, 14);
		contentPane.add(lblProfisso);

		JLabel lblPraticaEsporte = new JLabel("Pratica esporte");
		lblPraticaEsporte.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPraticaEsporte.setBounds(285, 168, 104, 33);
		contentPane.add(lblPraticaEsporte);

		tfNome = new JTextField();
		tfNome.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNome.setBounds(365, 47, 430, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);

		tfEndereco = new JTextField();
		tfEndereco.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(365, 98, 159, 20);
		contentPane.add(tfEndereco);

		tfCep = new JTextField();
		tfCep.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCep.setColumns(10);
		tfCep.setBounds(365, 123, 159, 20);
		contentPane.add(tfCep);

		tfProfissao = new JTextField();
		tfProfissao.setFont(new Font("Arial", Font.PLAIN, 14));
		tfProfissao.setColumns(10);
		tfProfissao.setBounds(365, 148, 159, 20);
		contentPane.add(tfProfissao);

		JComboBox cbSexo = new JComboBox();
		cbSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));
		cbSexo.setFont(new Font("Arial", Font.PLAIN, 14));
		cbSexo.setBounds(365, 70, 121, 20);
		contentPane.add(cbSexo);

		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDataDeNascimento.setBounds(524, 73, 154, 14);
		contentPane.add(lblDataDeNascimento);

		MaskFormatter data = new MaskFormatter("##-##-####");
		tfDataNascimento = new JFormattedTextField(data);
		tfDataNascimento.setFont(new Font("Arial", Font.PLAIN, 14));
		tfDataNascimento.setBounds(682, 70, 113, 20);
		contentPane.add(tfDataNascimento);
		tfDataNascimento.setColumns(10);

		JLabel lblEstadoCivil = new JLabel("Estado civil");
		lblEstadoCivil.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEstadoCivil.setBounds(562, 202, 79, 14);
		contentPane.add(lblEstadoCivil);

		tfEstadoCivil = new JTextField();
		tfEstadoCivil.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEstadoCivil.setColumns(10);
		tfEstadoCivil.setBounds(650, 199, 144, 20);
		contentPane.add(tfEstadoCivil);

		JLabel lblIndicao = new JLabel("Bairro");
		lblIndicao.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIndicao.setBounds(562, 126, 70, 17);
		contentPane.add(lblIndicao);

		tfBairro = new JTextField();
		tfBairro.setFont(new Font("Arial", Font.PLAIN, 14));
		tfBairro.setColumns(10);
		tfBairro.setBounds(650, 123, 144, 20);
		contentPane.add(tfBairro);

		JRadioButton rdSimEsporte = new JRadioButton("Sim");
		rdSimEsporte.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup.add(rdSimEsporte);
		rdSimEsporte.setBounds(395, 173, 53, 23);
		contentPane.add(rdSimEsporte);

		JRadioButton rdNaoEsporte = new JRadioButton("N\u00E3o");
		rdNaoEsporte.setFont(new Font("Arial", Font.PLAIN, 14));
		rdNaoEsporte.setSelected(true);
		buttonGroup.add(rdNaoEsporte);
		rdNaoEsporte.setBounds(457, 173, 53, 23);
		contentPane.add(rdNaoEsporte);

		JLabel lblQual = new JLabel("Qual");
		lblQual.setFont(new Font("Arial", Font.PLAIN, 14));
		lblQual.setBounds(562, 177, 40, 14);
		contentPane.add(lblQual);

		tfEsporte = new JTextField();
		tfEsporte.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEsporte.setColumns(10);
		tfEsporte.setBounds(651, 174, 144, 20);
		contentPane.add(tfEsporte);

		JLabel lblCaladoDeUso = new JLabel("Cal\u00E7ado de uso frequente");
		lblCaladoDeUso.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCaladoDeUso.setBounds(285, 199, 172, 20);
		contentPane.add(lblCaladoDeUso);

		tfCalcado = new JTextField();
		tfCalcado.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCalcado.setColumns(10);
		tfCalcado.setBounds(457, 199, 89, 20);
		contentPane.add(tfCalcado);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 291, 1029, 2);
		contentPane.add(separator);

		JLabel lblExameFisico = new JLabel("Exame f\u00EDsico");
		lblExameFisico.setHorizontalAlignment(SwingConstants.CENTER);
		lblExameFisico.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblExameFisico.setBounds(545, 301, 494, 25);
		contentPane.add(lblExameFisico);

		JCheckBox chOnicomicose = new JCheckBox("Onicomicose");
		chOnicomicose.setFont(new Font("Arial", Font.PLAIN, 14));
		chOnicomicose.setBounds(562, 333, 144, 23);
		contentPane.add(chOnicomicose);

		JCheckBox chOnicogrifose = new JCheckBox("Onicogrifose");
		chOnicogrifose.setFont(new Font("Arial", Font.PLAIN, 14));
		chOnicogrifose.setBounds(562, 359, 144, 23);
		contentPane.add(chOnicogrifose);

		JCheckBox chOnicocriptose = new JCheckBox("Onicocriptose");
		chOnicocriptose.setFont(new Font("Arial", Font.PLAIN, 14));
		chOnicocriptose.setBounds(562, 385, 144, 23);
		contentPane.add(chOnicocriptose);

		JCheckBox chGranuloma = new JCheckBox("Granuloma");
		chGranuloma.setFont(new Font("Arial", Font.PLAIN, 14));
		chGranuloma.setBounds(712, 333, 126, 23);
		contentPane.add(chGranuloma);

		JCheckBox chOnicolise = new JCheckBox("Onicolise");
		chOnicolise.setFont(new Font("Arial", Font.PLAIN, 14));
		chOnicolise.setBounds(712, 360, 126, 23);
		contentPane.add(chOnicolise);

		JCheckBox chOnicofose = new JCheckBox("Onicofose");
		chOnicofose.setFont(new Font("Arial", Font.PLAIN, 14));
		chOnicofose.setBounds(712, 386, 126, 23);
		contentPane.add(chOnicofose);

		JCheckBox chParoniquea = new JCheckBox("Paron\u00EDquea");
		chParoniquea.setFont(new Font("Arial", Font.PLAIN, 14));
		chParoniquea.setBounds(840, 333, 199, 23);
		contentPane.add(chParoniquea);

		JCheckBox chHematoma = new JCheckBox("Hematoma subungueal");
		chHematoma.setFont(new Font("Arial", Font.PLAIN, 14));
		chHematoma.setBounds(840, 358, 199, 23);
		contentPane.add(chHematoma);

		JCheckBox chNormais = new JCheckBox("Unhas normais");
		chNormais.setFont(new Font("Arial", Font.PLAIN, 14));
		chNormais.setBounds(840, 385, 199, 23);
		contentPane.add(chNormais);

		JLabel lblObs = new JLabel("Obs.");
		lblObs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblObs.setBounds(562, 418, 46, 14);
		contentPane.add(lblObs);

		tfObs1 = new JTextField();
		tfObs1.setFont(new Font("Arial", Font.PLAIN, 14));
		tfObs1.setColumns(10);
		tfObs1.setBounds(642, 415, 364, 20);
		contentPane.add(tfObs1);

		JCheckBox chCalos = new JCheckBox("Calos");
		chCalos.setFont(new Font("Arial", Font.PLAIN, 14));
		chCalos.setBounds(562, 439, 144, 23);
		contentPane.add(chCalos);

		JCheckBox chVerrugaPlantar = new JCheckBox("Verruga Plantar");
		chVerrugaPlantar.setFont(new Font("Arial", Font.PLAIN, 14));
		chVerrugaPlantar.setBounds(562, 465, 144, 23);
		contentPane.add(chVerrugaPlantar);

		JCheckBox chAnidrose = new JCheckBox("Anidrose");
		chAnidrose.setFont(new Font("Arial", Font.PLAIN, 14));
		chAnidrose.setBounds(562, 491, 144, 23);
		contentPane.add(chAnidrose);

		JCheckBox chCalosidades = new JCheckBox("Calosidades");
		chCalosidades.setFont(new Font("Arial", Font.PLAIN, 14));
		chCalosidades.setBounds(712, 439, 126, 23);
		contentPane.add(chCalosidades);

		JCheckBox chFissuras = new JCheckBox("Fissuras");
		chFissuras.setFont(new Font("Arial", Font.PLAIN, 14));
		chFissuras.setBounds(712, 466, 126, 23);
		contentPane.add(chFissuras);

		JCheckBox chHiperidrose = new JCheckBox("Hiperidrose");
		chHiperidrose.setFont(new Font("Arial", Font.PLAIN, 14));
		chHiperidrose.setBounds(712, 492, 126, 23);
		contentPane.add(chHiperidrose);

		JCheckBox chHiperqueratose = new JCheckBox("Hiperqueratose");
		chHiperqueratose.setFont(new Font("Arial", Font.PLAIN, 14));
		chHiperqueratose.setBounds(840, 439, 199, 23);
		contentPane.add(chHiperqueratose);

		JCheckBox chUlcera = new JCheckBox("\u00DAlcera");
		chUlcera.setFont(new Font("Arial", Font.PLAIN, 14));
		chUlcera.setBounds(840, 464, 199, 23);
		contentPane.add(chUlcera);

		JCheckBox chBromidrose = new JCheckBox("Bromidrose");
		chBromidrose.setFont(new Font("Arial", Font.PLAIN, 14));
		chBromidrose.setBounds(840, 491, 199, 23);
		contentPane.add(chBromidrose);

		JCheckBox chTinea = new JCheckBox("Tinea interigital");
		chTinea.setFont(new Font("Arial", Font.PLAIN, 14));
		chTinea.setBounds(562, 519, 144, 23);
		contentPane.add(chTinea);

		JCheckBox chOutras = new JCheckBox("Outras");
		chOutras.setFont(new Font("Arial", Font.PLAIN, 14));
		chOutras.setBounds(562, 545, 144, 23);
		contentPane.add(chOutras);

		JCheckBox chEdema = new JCheckBox("Edema");
		chEdema.setFont(new Font("Arial", Font.PLAIN, 14));
		chEdema.setBounds(562, 571, 144, 23);
		contentPane.add(chEdema);

		JCheckBox chDisidrose = new JCheckBox("Disidrose");
		chDisidrose.setFont(new Font("Arial", Font.PLAIN, 14));
		chDisidrose.setBounds(712, 519, 126, 23);
		contentPane.add(chDisidrose);

		JCheckBox chCianose = new JCheckBox("Cianose");
		chCianose.setFont(new Font("Arial", Font.PLAIN, 14));
		chCianose.setBounds(840, 544, 199, 23);
		contentPane.add(chCianose);

		JCheckBox chEritema = new JCheckBox("Eritema");
		chEritema.setFont(new Font("Arial", Font.PLAIN, 14));
		chEritema.setBounds(712, 572, 126, 23);
		contentPane.add(chEritema);

		JCheckBox chPsoriase = new JCheckBox("Psor\u00EDase");
		chPsoriase.setFont(new Font("Arial", Font.PLAIN, 14));
		chPsoriase.setBounds(840, 519, 199, 23);
		contentPane.add(chPsoriase);

		JCheckBox chBolhas = new JCheckBox("Bolhas");
		chBolhas.setFont(new Font("Arial", Font.PLAIN, 14));
		chBolhas.setBounds(712, 545, 126, 23);
		contentPane.add(chBolhas);

		JCheckBox chUmidade = new JCheckBox("Umidade interdigital");
		chUmidade.setFont(new Font("Arial", Font.PLAIN, 14));
		chUmidade.setBounds(840, 571, 199, 23);
		contentPane.add(chUmidade);

		JLabel label = new JLabel("Obs.");
		label.setFont(new Font("Arial", Font.PLAIN, 14));
		label.setBounds(562, 604, 46, 14);
		contentPane.add(label);

		tfObs2 = new JTextField();
		tfObs2.setFont(new Font("Arial", Font.PLAIN, 14));
		tfObs2.setColumns(10);
		tfObs2.setBounds(642, 601, 364, 20);
		contentPane.add(tfObs2);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(55, 859, 419, 2);
		contentPane.add(separator_1);

		JLabel lblHistriaClnica = new JLabel("Hist\u00F3ria cl\u00EDnica");
		lblHistriaClnica.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistriaClnica.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblHistriaClnica.setBounds(10, 305, 494, 25);
		contentPane.add(lblHistriaClnica);

		JCheckBox chDiabetes = new JCheckBox("Diabetes");
		chDiabetes.setFont(new Font("Arial", Font.PLAIN, 14));
		chDiabetes.setBounds(20, 333, 149, 23);
		contentPane.add(chDiabetes);

		JCheckBox chCardiopatia = new JCheckBox("Cardiopatia");
		chCardiopatia.setFont(new Font("Arial", Font.PLAIN, 14));
		chCardiopatia.setBounds(20, 359, 149, 23);
		contentPane.add(chCardiopatia);

		JCheckBox chTrombose = new JCheckBox("Trombose");
		chTrombose.setFont(new Font("Arial", Font.PLAIN, 14));
		chTrombose.setBounds(20, 385, 149, 23);
		contentPane.add(chTrombose);

		JCheckBox chArtrose = new JCheckBox("Artrose");
		chArtrose.setFont(new Font("Arial", Font.PLAIN, 14));
		chArtrose.setBounds(171, 386, 126, 23);
		contentPane.add(chArtrose);

		JCheckBox chOsteoporose = new JCheckBox("Osteoporose");
		chOsteoporose.setFont(new Font("Arial", Font.PLAIN, 14));
		chOsteoporose.setBounds(171, 360, 126, 23);
		contentPane.add(chOsteoporose);

		JCheckBox chColesterol = new JCheckBox("Colesterol");
		chColesterol.setFont(new Font("Arial", Font.PLAIN, 14));
		chColesterol.setBounds(171, 333, 126, 23);
		contentPane.add(chColesterol);

		JCheckBox chHipertensao = new JCheckBox("Hipertens\u00E3o");
		chHipertensao.setFont(new Font("Arial", Font.PLAIN, 14));
		chHipertensao.setBounds(299, 333, 113, 23);
		contentPane.add(chHipertensao);

		JCheckBox chHepatite = new JCheckBox("Hepatite");
		chHepatite.setFont(new Font("Arial", Font.PLAIN, 14));
		chHepatite.setBounds(299, 358, 171, 23);
		contentPane.add(chHepatite);

		JCheckBox chHipotireoidismo = new JCheckBox("Hipotireoidismo");
		chHipotireoidismo.setFont(new Font("Arial", Font.PLAIN, 14));
		chHipotireoidismo.setBounds(299, 385, 171, 23);
		contentPane.add(chHipotireoidismo);

		JCheckBox chHipertireoidismo = new JCheckBox("Hipertireoidismo");
		chHipertireoidismo.setFont(new Font("Arial", Font.PLAIN, 14));
		chHipertireoidismo.setBounds(299, 411, 163, 23);
		contentPane.add(chHipertireoidismo);

		JCheckBox chTabagista = new JCheckBox("Tabagista");
		chTabagista.setFont(new Font("Arial", Font.PLAIN, 14));
		chTabagista.setBounds(20, 411, 149, 23);
		contentPane.add(chTabagista);

		JCheckBox chMarcapasso = new JCheckBox("Marcapasso");
		chMarcapasso.setFont(new Font("Arial", Font.PLAIN, 14));
		chMarcapasso.setBounds(20, 437, 149, 23);
		contentPane.add(chMarcapasso);

		JCheckBox chEtilista = new JCheckBox("Etilista");
		chEtilista.setFont(new Font("Arial", Font.PLAIN, 14));
		chEtilista.setBounds(171, 411, 126, 23);
		contentPane.add(chEtilista);

		JCheckBox chCancerigeno = new JCheckBox("Antecedentes cancer\u00EDgenos");
		chCancerigeno.setFont(new Font("Arial", Font.PLAIN, 14));
		chCancerigeno.setBounds(299, 437, 225, 23);
		contentPane.add(chCancerigeno);

		JCheckBox chCirculatorio = new JCheckBox("Problemas circulat\u00F3rios");
		chCirculatorio.setFont(new Font("Arial", Font.PLAIN, 14));
		chCirculatorio.setBounds(20, 463, 208, 23);
		contentPane.add(chCirculatorio);

		JCheckBox chConvulsao = new JCheckBox("Convuls\u00E3o");
		chConvulsao.setFont(new Font("Arial", Font.PLAIN, 14));
		chConvulsao.setBounds(171, 437, 126, 23);
		contentPane.add(chConvulsao);

		JLabel lblOutros = new JLabel("Outros");
		lblOutros.setFont(new Font("Arial", Font.PLAIN, 14));
		lblOutros.setBounds(20, 500, 46, 14);
		contentPane.add(lblOutros);

		tfOutros = new JTextField();
		tfOutros.setFont(new Font("Arial", Font.PLAIN, 14));
		tfOutros.setColumns(10);
		tfOutros.setBounds(85, 495, 398, 20);
		contentPane.add(tfOutros);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(542, 304, 2, 402);
		contentPane.add(separator_2);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					p.setNome(tfNome.getText()+"");
					p.setEndereco(tfEndereco.getText()+"");
					p.setSexo(String.valueOf(cbSexo.getSelectedItem()));
					p.setTelefone(tfTelefone.getText()+"");
					p.setCidade(tfCidade.getText()+"");
					String dataNascimento = tfDataNascimento.getText();
					String[] dataFinal = dataNascimento.split("-");
					p.setDataNascimento(dataFinal[2] + "-" + dataFinal[1] + "-" + dataFinal[0]);
					p.setCep(tfCep.getText()+"");
					p.setBairro(tfBairro.getText()+"");
					p.setEstadoCivil(tfEstadoCivil.getText()+"");
					p.setProfissao(tfProfissao.getText()+"");
					p.setIndicacao(tfBairro.getText()+"");
					if (rdSimEsporte.isSelected()) {
						p.setEsporte(tfEsporte.getText()+"");
					}
					p.setCalcado(tfCalcado.getText()+"");
					p.setEmail(tfEmail.getText()+"");

					if (chOnicomicose.isSelected()) {
						problemasFisicos.add(1);
					}
					if (chGranuloma.isSelected()) {
						problemasFisicos.add(2);
					}
					if (chParoniquea.isSelected()) {
						problemasFisicos.add(3);
					}
					if (chOnicogrifose.isSelected()) {
						problemasFisicos.add(4);
					}
					if (chOnicolise.isSelected()) {
						problemasFisicos.add(5);
					}
					if (chHematoma.isSelected()) {
						problemasFisicos.add(6);
					}
					if (chOnicocriptose.isSelected()) {
						problemasFisicos.add(7);
					}
					if (chOnicofose.isSelected()) {
						problemasFisicos.add(8);
					}
					if (chNormais.isSelected()) {
						problemasFisicos.add(9);
					}
					if (chCalos.isSelected()) {
						problemasFisicos.add(10);
					}
					if (chCalosidades.isSelected()) {
						problemasFisicos.add(11);
					}
					if (chHiperqueratose.isSelected()) {
						problemasFisicos.add(12);
					}
					if (chVerrugaPlantar.isSelected()) {
						problemasFisicos.add(13);
					}
					if (chFissuras.isSelected()) {
						problemasFisicos.add(14);
					}
					if (chUlcera.isSelected()) {
						problemasFisicos.add(15);
					}
					if (chAnidrose.isSelected()) {
						problemasFisicos.add(16);
					}
					if (chHiperidrose.isSelected()) {
						problemasFisicos.add(17);
					}
					if (chBromidrose.isSelected()) {
						problemasFisicos.add(18);
					}
					if (chTinea.isSelected()) {
						problemasFisicos.add(19);
					}
					if (chDisidrose.isSelected()) {
						problemasFisicos.add(20);
					}
					if (chPsoriase.isSelected()) {
						problemasFisicos.add(21);
					}
					if (chOutras.isSelected()) {
						problemasFisicos.add(22);
					}
					if (chBolhas.isSelected()) {
						problemasFisicos.add(23);
					}
					if (chCianose.isSelected()) {
						problemasFisicos.add(24);
					}
					if (chEdema.isSelected()) {
						problemasFisicos.add(25);
					}
					if (chEritema.isSelected()) {
						problemasFisicos.add(26);
					}
					if (chUmidade.isSelected()) {
						problemasFisicos.add(27);
					}

					ef.setObs1(tfObs1.getText());
					ef.setObs2(tfObs2.getText());

					if (rdFunil.isSelected()) {
						ef.setUnhas(rdFunil.getText());
					} else if (rdGancho.isSelected()) {
						ef.setUnhas(rdGancho.getText());
					} else if (rdTorques.isSelected()) {
						ef.setUnhas(rdTorques.getText());
					} else {
						ef.setUnhas(rdCaracol.getText());
					}

					hc.setAlergia(tfAlergia.getText());
					hc.setCirurgia(tfCirurgia.getText());
					if (rdPouca.isSelected()) {
						hc.setDor(rdPouca.getText());
					} else if (rdMuita.isSelected()) {
						hc.setDor(rdMuita.getText());
					} else if (rdSuportavel.isSelected()) {
						hc.setDor(rdSuportavel.getText());
					} else {
						hc.setDor(rdNenhuma.getText());
					}
					hc.setMedicamento(tfMedicamento.getText());

					if (chDiabetes.isSelected()) {
						problemasClinicos.add(1);
					}
					if (chColesterol.isSelected()) {
						problemasClinicos.add(2);
					}
					if (chHipertensao.isSelected()) {
						problemasClinicos.add(3);
					}
					if (chCardiopatia.isSelected()) {
						problemasClinicos.add(4);
					}
					if (chOsteoporose.isSelected()) {
						problemasClinicos.add(5);
					}
					if (chHepatite.isSelected()) {
						problemasClinicos.add(6);
					}
					if (chTrombose.isSelected()) {
						problemasClinicos.add(7);
					}
					if (chArtrose.isSelected()) {
						problemasClinicos.add(8);
					}
					if (chHipotireoidismo.isSelected()) {
						problemasClinicos.add(9);
					}
					if (chTabagista.isSelected()) {
						problemasClinicos.add(10);
					}
					if (chEtilista.isSelected()) {
						problemasClinicos.add(11);
					}
					if (chHipertireoidismo.isSelected()) {
						problemasClinicos.add(12);
					}
					if (chMarcapasso.isSelected()) {
						problemasClinicos.add(13);
					}
					if (chConvulsao.isSelected()) {
						problemasClinicos.add(14);
					}
					if (chCancerigeno.isSelected()) {
						problemasClinicos.add(15);
					}
					if (chCirculatorio.isSelected()) {
						problemasClinicos.add(16);
					}

					hcDao.cadastrarHistoriaClinica(hc, problemasClinicos);
					efDao.cadastrarExameFisico(ef, problemasFisicos);
					pDao.CadastrarPaciente(p);
					problemasFisicos.clear();
					problemasClinicos.clear();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		btnFinalizar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnFinalizar.setBounds(562, 739, 182, 33);
		contentPane.add(btnFinalizar);

		JLabel lblTomaMedicamento = new JLabel("Toma algum medicamento");
		lblTomaMedicamento.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTomaMedicamento.setBounds(20, 574, 223, 23);
		contentPane.add(lblTomaMedicamento);

		rdSimMedicamento.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_1.add(rdSimMedicamento);
		rdSimMedicamento.setBounds(278, 571, 53, 23);
		contentPane.add(rdSimMedicamento);

		rdNaoMedicamento.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_1.add(rdNaoMedicamento);
		rdNaoMedicamento.setSelected(true);
		rdNaoMedicamento.setBounds(335, 571, 53, 23);
		contentPane.add(rdNaoMedicamento);

		JLabel label_2 = new JLabel("Qual");
		label_2.setFont(new Font("Arial", Font.PLAIN, 14));
		label_2.setBounds(20, 602, 40, 14);
		contentPane.add(label_2);

		tfMedicamento = new JTextField();
		tfMedicamento.setFont(new Font("Arial", Font.PLAIN, 14));
		tfMedicamento.setColumns(10);
		tfMedicamento.setBounds(85, 600, 398, 20);
		contentPane.add(tfMedicamento);

		JLabel lblAlgergiaAAlgum = new JLabel("Alergia a algum medicamento");
		lblAlgergiaAAlgum.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAlgergiaAAlgum.setBounds(20, 627, 208, 21);
		contentPane.add(lblAlgergiaAAlgum);

		rdSimAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_2.add(rdSimAlergia);
		rdSimAlergia.setBounds(278, 628, 53, 23);
		contentPane.add(rdSimAlergia);

		rdNaoAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_2.add(rdNaoAlergia);
		rdNaoAlergia.setSelected(true);
		rdNaoAlergia.setBounds(336, 628, 53, 23);
		contentPane.add(rdNaoAlergia);

		JLabel label_3 = new JLabel("Qual");
		label_3.setFont(new Font("Arial", Font.PLAIN, 14));
		label_3.setBounds(20, 654, 40, 14);
		contentPane.add(label_3);

		tfAlergia = new JTextField();
		tfAlergia.setFont(new Font("Arial", Font.PLAIN, 14));
		tfAlergia.setColumns(10);
		tfAlergia.setBounds(85, 651, 398, 20);
		contentPane.add(tfAlergia);

		JLabel lblCirurgiaEmMembros = new JLabel("Cirurgia em membros inferiores");
		lblCirurgiaEmMembros.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCirurgiaEmMembros.setBounds(20, 522, 223, 33);
		contentPane.add(lblCirurgiaEmMembros);

		tfCirurgia = new JTextField();
		tfCirurgia.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCirurgia.setColumns(10);
		tfCirurgia.setBounds(20, 549, 463, 20);
		contentPane.add(tfCirurgia);

		JLabel lblSensibilidadeADor = new JLabel("Sensibilidade a dor");
		lblSensibilidadeADor.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSensibilidadeADor.setBounds(20, 679, 134, 27);
		contentPane.add(lblSensibilidadeADor);

		rdMuita.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_3.add(rdMuita);
		rdMuita.setBounds(160, 678, 70, 28);
		contentPane.add(rdMuita);

		rdPouca.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_3.add(rdPouca);
		rdPouca.setSelected(true);
		rdPouca.setBounds(232, 678, 76, 28);
		contentPane.add(rdPouca);

		rdSuportavel.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_3.add(rdSuportavel);
		rdSuportavel.setBounds(310, 678, 109, 28);
		contentPane.add(rdSuportavel);

		rdNenhuma.setFont(new Font("Arial", Font.PLAIN, 14));
		buttonGroup_3.add(rdNenhuma);
		rdNenhuma.setSelected(true);
		rdNenhuma.setBounds(421, 679, 113, 28);
		contentPane.add(rdNenhuma);

		JLabel lblFormatoDasUnhas = new JLabel("Formato das unhas");
		lblFormatoDasUnhas.setHorizontalAlignment(SwingConstants.CENTER);
		lblFormatoDasUnhas.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblFormatoDasUnhas.setBounds(545, 629, 494, 25);
		contentPane.add(lblFormatoDasUnhas);

		buttonGroup_4.add(rdFunil);
		rdFunil.setFont(new Font("Arial", Font.PLAIN, 14));
		rdFunil.setBounds(627, 661, 79, 23);
		contentPane.add(rdFunil);

		buttonGroup_4.add(rdGancho);
		rdGancho.setFont(new Font("Arial", Font.PLAIN, 14));
		rdGancho.setBounds(714, 661, 89, 23);
		contentPane.add(rdGancho);

		buttonGroup_4.add(rdTorques);
		rdTorques.setFont(new Font("Arial", Font.PLAIN, 14));
		rdTorques.setBounds(805, 661, 89, 23);
		contentPane.add(rdTorques);

		buttonGroup_4.add(rdCaracol);
		rdCaracol.setFont(new Font("Arial", Font.PLAIN, 14));
		rdCaracol.setSelected(true);
		rdCaracol.setBounds(896, 661, 102, 23);
		contentPane.add(rdCaracol);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(285, 233, 70, 14);
		contentPane.add(lblEmail);

		tfEmail = new JTextField();
		tfEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEmail.setColumns(10);
		tfEmail.setBounds(365, 230, 159, 20);
		contentPane.add(tfEmail);

		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTelefone.setBounds(562, 232, 70, 17);
		contentPane.add(lblTelefone);

		tfTelefone = new JTextField();
		tfTelefone.setFont(new Font("Arial", Font.PLAIN, 14));
		tfTelefone.setColumns(10);
		tfTelefone.setBounds(651, 230, 144, 20);
		contentPane.add(tfTelefone);

		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCidade.setBounds(562, 101, 70, 14);
		contentPane.add(lblCidade);

		tfCidade = new JTextField();
		tfCidade.setFont(new Font("Arial", Font.PLAIN, 14));
		tfCidade.setColumns(10);
		tfCidade.setBounds(651, 98, 144, 20);
		contentPane.add(tfCidade);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CadastroView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		label_1.setBounds(10, 0, 263, 196);
		contentPane.add(label_1);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(342, 739, 182, 33);
		contentPane.add(btnVoltar);

		JLabel lblIndicao_1 = new JLabel("Indica\u00E7\u00E3o");
		lblIndicao_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIndicao_1.setBounds(562, 150, 70, 17);
		contentPane.add(lblIndicao_1);

		tfIndicacao = new JTextField();
		tfIndicacao.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfIndicacao.setColumns(10);
		tfIndicacao.setBounds(651, 148, 144, 20);
		contentPane.add(tfIndicacao);
	}
}

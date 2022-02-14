package br.edu.ifc.videira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import br.edu.ifc.videira.beans.Paciente;
import br.edu.ifc.videira.utils.Conexao;

public class PacienteDAO {

	public void CadastrarPaciente(Paciente p) throws SQLException, Exception {
		try {
			String sql = "INSERT INTO paciente (nome,endereco,sexo,telefone,cidade,bairro,cep,dataNascimento,estadoCivil,profissao,email,indicacao,esporte,calcado) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, p.getNome());
			sqlPrep.setString(contador++, p.getEndereco());
			sqlPrep.setString(contador++, p.getSexo());
			sqlPrep.setString(contador++, p.getTelefone());
			sqlPrep.setString(contador++, p.getCidade());
			sqlPrep.setString(contador++, p.getBairro());
			sqlPrep.setString(contador++, p.getCep());
			sqlPrep.setString(contador++, p.getDataNascimento());
			sqlPrep.setString(contador++, p.getEstadoCivil());
			sqlPrep.setString(contador++, p.getProfissao());
			sqlPrep.setString(contador++, p.getEmail());
			sqlPrep.setString(contador++, p.getIndicacao());
			sqlPrep.setString(contador++, p.getEsporte());
			sqlPrep.setString(contador++, p.getCalcado());
			sqlPrep.execute();

			String sql2 = "INSERT INTO anamnese (idHistoriaClinica,idExameFisico,idPaciente) VALUES (?,?,?)";
			contador = 1;
			int ids[] = this.buscarIds();
			java.sql.PreparedStatement sqlPrep2 = Conexao.conectar().prepareStatement(sql2);
			sqlPrep2.setInt(contador++, ids[0]);
			sqlPrep2.setInt(contador++, ids[1]);
			sqlPrep2.setInt(contador++, ids[2]);
			sqlPrep2.execute();
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void alterarPaciente(Paciente pa) throws Exception {
		try {
			String sql = "UPDATE paciente SET nome=?, sexo=?, endereco=?, cidade=?, bairro=?, cep=?,telefone=?,dataNascimento=?,estadoCivil=?,profissao=?,email=?,indicacao=?,esporte=?,calcado=? WHERE id=?";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, pa.getNome());
			sqlPrep.setString(contador++, pa.getSexo());
			sqlPrep.setString(contador++, pa.getEndereco());
			sqlPrep.setString(contador++, pa.getCidade());
			sqlPrep.setString(contador++, pa.getBairro());
			sqlPrep.setString(contador++, pa.getCep());
			sqlPrep.setString(contador++, pa.getTelefone());
			sqlPrep.setString(contador++, pa.getDataNascimento());
			sqlPrep.setString(contador++, pa.getEstadoCivil());
			sqlPrep.setString(contador++, pa.getProfissao());
			sqlPrep.setString(contador++, pa.getEmail());
			sqlPrep.setString(contador++, pa.getIndicacao());
			sqlPrep.setString(contador++, pa.getEsporte());
			sqlPrep.setString(contador++, pa.getCalcado());
			sqlPrep.setInt(contador++, pa.getId());
			sqlPrep.execute();
			JOptionPane.showMessageDialog(null, "Paciente alterado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public Map<String, String> buscarPaciente(int idAnamnese) throws SQLException, Exception {
		Map<String, String> paciente = new HashMap<String, String>();
		try {
			String sql = "SELECT * FROM paciente,anamnese WHERE paciente.id = anamnese.idPaciente AND anamnese.id= "
					+ idAnamnese;
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				paciente.put("nome", rs.getString("nome"));
				paciente.put("endereco", rs.getString("endereco"));
				paciente.put("sexo", rs.getString("sexo"));
				paciente.put("telefone", rs.getString("telefone"));
				paciente.put("cidade", rs.getString("cidade"));
				paciente.put("bairro", rs.getString("bairro"));
				paciente.put("cep", rs.getString("cep"));
				String data = rs.getString("dataNascimento");
				String[] dataFormatada = data.split("-");
				paciente.put("dataNascimento", dataFormatada[2] + "-" + dataFormatada[1] + "-" + dataFormatada[0]);
				paciente.put("estadoCivil", rs.getString("estadoCivil"));
				paciente.put("profissao", rs.getString("profissao"));
				paciente.put("email", rs.getString("email"));
				paciente.put("indicacao", rs.getString("indicacao"));
				paciente.put("esporte", rs.getString("esporte"));
				paciente.put("calcado", rs.getString("calcado"));
			}
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return paciente;
	}

	public void excluirPaciente(int idAnamnese) throws SQLException, Exception {
		try {
			String sql = "DELETE FROM anamnese WHERE id = ?";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			sqlPrep.setInt(1, idAnamnese);
			sqlPrep.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private int[] buscarIds() throws SQLException, Exception {
		int ids[] = new int[3];
		try {
			String sql = "select max(historiaClinica.id) as idHistoriaClinica,max(exameFisico.id) as idExameFisico, max(paciente.id) as idPaciente from historiaClinica,exameFisico,paciente";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				ids[0] = Integer.parseInt(rs.getString(1));
				ids[1] = Integer.parseInt(rs.getString(2));
				ids[2] = Integer.parseInt(rs.getString(3));
			}
			state.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return ids;
	}

	public List<Object> buscar() throws SQLException, Exception {
		List<Object> cliente = new ArrayList<Object>();
		try {
			String sql = "SELECT anamnese.id,paciente.nome,paciente.dataNascimento,paciente.cidade,paciente.email FROM paciente,anamnese WHERE anamnese.idPaciente = paciente.id ORDER BY paciente.id DESC";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				String data = rs.getString(3);
				String[] dataFormatada = data.split("-");
				Object[] linha = { rs.getString(1), rs.getString(2),
						dataFormatada[2] + "-" + dataFormatada[1] + "-" + dataFormatada[0], rs.getString(4),
						rs.getString(5) };
				cliente.add(linha);
			}
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cliente;
	}
}

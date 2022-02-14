package br.edu.ifc.videira.DAOs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import br.edu.ifc.videira.beans.Tratamento;
import br.edu.ifc.videira.utils.Conexao;

public class TratamentoDao {
	public void cadastrarTratamento(Tratamento tr) throws SQLException, Exception {
		try {

			tr.setIdAnamnese(this.retornaIdAnamnese(tr.getIdPaciente()));
			if (this.retornaStatus(tr.getIdAnamnese()) == 1) {
				tr.setIdTratamento(this.retornaIdTratamento(tr));
				this.cadastrarProcedimento_Tratamento(tr);
				JOptionPane.showMessageDialog(null, "Adicionado ao tratamento");
			} else {
				String sql = "INSERT INTO tratamento (idAnamnese,status) VALUES (?,?)";
				java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
				int contador = 1;
				sqlPrep.setInt(contador++, tr.getIdAnamnese());
				sqlPrep.setInt(contador++, 0);
				sqlPrep.execute();
				tr.setIdTratamento(this.retornaMaxIdTratamento());
				this.cadastrarProcedimento_Tratamento(tr);
				JOptionPane.showMessageDialog(null, "Tratamento iniciado");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void finalizarTratamento(int idTratamento) throws SQLException, Exception {
		try {
			String sql = "UPDATE tratamento SET status = 1 WHERE id = ?";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setInt(contador++, idTratamento);
			sqlPrep.execute();
			JOptionPane.showMessageDialog(null, "Tratamento finalizado");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public List<Object> buscarTratamento(int idTratamento) throws SQLException, Exception {
		List<Object> tratamentos = new ArrayList<Object>();
		try {
			String sql = "select procedimento_tratamento.id,procedimento.nome,procedimento.preco,procedimento_tratamento.data,procedimento_tratamento.observacao from procedimento,tratamento,procedimento_tratamento\n"
					+ "where procedimento.id = procedimento_tratamento.idProcedimento\n"
					+ "and tratamento.id = procedimento_tratamento.idTratamento\n" + "and tratamento.id = "
					+ idTratamento;
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				String data = rs.getString(4);
				String[] dataFormatada = data.split("-");
				Object[] linha = { rs.getString(1), rs.getString(2), rs.getString(3),
						dataFormatada[2] + "-" + dataFormatada[1] + "-" + dataFormatada[0], rs.getString(5) };
				tratamentos.add(linha);
			}
			state.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return tratamentos;
	}

	public List<Object> buscarUltimosTratamento() throws SQLException, Exception {
		List<Object> tratamentos = new ArrayList<Object>();
		try {
			String sql = "select t1.id,paciente.nome,procedimento.nome,procedimento_tratamento.`data`,t1.`status`\n"
					+ "from paciente,anamnese,tratamento as t1,procedimento_tratamento,procedimento\n"
					+ "where paciente.id = anamnese.idPaciente and anamnese.id = t1.idAnamnese\n"
					+ "and procedimento_tratamento.idProcedimento = procedimento.id\n"
					+ "and procedimento_tratamento.idTratamento = t1.id\n" + "and procedimento_tratamento.id = \n"
					+ "(select max(procedimento_tratamento.id) from \n"
					+ "	procedimento_tratamento,tratamento as t2 where t2.id = procedimento_tratamento.idTratamento \n"
					+ "    and t1.id = procedimento_tratamento.idTratamento order by procedimento_tratamento.id desc limit 1)\n"
					+ "order by t1.id desc";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Object[] linha = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						Integer.parseInt((rs.getString(5))) == 0 ? "Andamento" : "Finalizado" };
				tratamentos.add(linha);
			}
			state.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return tratamentos;
	}

	private void cadastrarProcedimento_Tratamento(Tratamento tr) throws SQLException, Exception {
		try {
			String sql = "INSERT INTO procedimento_tratamento (idTratamento,idProcedimento,data,observacao) VALUES (?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setInt(contador++, tr.getIdTratamento());
			sqlPrep.setInt(contador++, tr.getIdProcedimento());
			sqlPrep.setDate(contador++, java.sql.Date.valueOf(java.time.LocalDate.now()));
			sqlPrep.setString(contador++, tr.getObservacao());
			sqlPrep.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private int retornaIdAnamnese(int idPaciente) throws Exception {
		try {
			String sql = "select anamnese.id as idPaciente from anamnese,paciente where anamnese.idPaciente = paciente.id and paciente.id = "
					+ idPaciente;
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("idPaciente");
			} else {
				return 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}

	private int retornaMaxIdTratamento() throws Exception {
		try {
			String sql = "select max(id) as id from tratamento";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}

	private int retornaIdTratamento(Tratamento tr) throws Exception {
		try {
			String sql = "select id from tratamento where status = 0 and idAnamnese = " + tr.getIdAnamnese();
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}

	private int retornaStatus(int idAnamnese) throws Exception {
		try {
			int cont = 0;
			String sql = "select status from tratamento where idAnamnese = " + idAnamnese;
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			while (rs.next()) {
				if (rs.getInt("status") == 0) {
					cont++;
				}
			}
			if (cont > 0) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return -1;
		}
	}
}

package br.edu.ifc.videira.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import br.edu.ifc.videira.utils.Conexao;

public class ProcedimentoDao {
	public List<Object> buscar() throws SQLException, Exception {
		List<Object> procedimentos = new ArrayList<Object>();
		try {
			String sql = "SELECT nome FROM procedimento";
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Object linha =  rs.getString(1);
				procedimentos.add(linha);
			}
			state.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return procedimentos;
	}

	public void excluirProcedimento(int idProcedimento) throws SQLException, Exception {
		try {
			String sql = "DELETE FROM procedimento_tratamento WHERE id = ?";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setInt(contador++, idProcedimento);
			sqlPrep.execute();
			JOptionPane.showMessageDialog(null, "Procedimento excluído");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}

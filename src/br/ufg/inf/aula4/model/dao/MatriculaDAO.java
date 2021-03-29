package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.model.entities.Aluno;
import br.ufg.inf.aula4.model.entities.Matricula;
import br.ufg.inf.aula4.model.entities.Oferta;
import br.ufg.inf.aula4.model.entities.Pessoa;
import br.ufg.inf.aula4.model.entities.Professor;
import br.ufg.inf.aula4.model.enums.Escolaridade;

public class MatriculaDAO {

	public int inserir(Matricula matricula) throws MatriculaException {
		PreparedStatement st = null;
		int id = -4;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_matricula " + "(id_aluno, id_oferta)" + "VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, matricula.getAluno().getIdAluno());
			st.setInt(2, matricula.getOferta().getIdOferta());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return id;
	}

	public List<Matricula> buscaTodos() throws MatriculaException {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Matricula> matriculas = new ArrayList<Matricula>();
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula ORDER BY id_matricula ";
			st = conn.prepareStatement(query);
			rs = st.executeQuery();
			while (rs.next()) {
				matriculas.add(this.vo(rs));
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matriculas;
	}

	private Matricula vo(ResultSet rs) throws SQLException {
		Matricula matricula = new Matricula();
		matricula.getAluno().setIdAluno((rs.getInt("id_aluno")));
		matricula.getOferta().setIdOferta((rs.getInt("id_oferta")));
		matricula.setIdMatricula((rs.getInt("id_aluno")));
		return matricula;
	}

	public Matricula buscaPorId(Integer id) throws MatriculaException {
		Matricula matricula = null;
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = "SELECT id_matricula, id_aluno, id_oferta FROM tb_matricula WHERE id_matricula = ? ";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				matricula = this.vo(rs);
			}
		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
		return matricula;
	}

//	public Professor alterar(Professor professor) throws MatriculaException {
//		PreparedStatement st = null;
//		try {
//			Connection conn = DB.getConnection();
//			String query = "UPDATE tb_professor SET escolaridade = ?, id_pessoa = ? WHERE id_professor = ? ; ";
//			st = (PreparedStatement) conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			st.setInt(1, professor.getEscolaridade().getId());
//			st.setInt(2, professor.getPessoa().getIdPessoa());
//			st.setInt(3, professor.getIdProfessor());
//			int rowsAffected = st.executeUpdate();
//			System.out.println("Linhas alteradas: " + rowsAffected);
//		} catch (SQLException e) {
//			throw new MatriculaException(e.getMessage());
//		}
//		return professor;
//	}

	public void excluir(Integer id) throws MatriculaException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			String query = " DELETE FROM tb_matricula WHERE id_matricula = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new MatriculaException(e.getMessage());
		}
	}
}

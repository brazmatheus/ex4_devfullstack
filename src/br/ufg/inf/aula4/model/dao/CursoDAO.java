package br.ufg.inf.aula4.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufg.inf.aula4.app.DB;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.model.entities.Curso;


public class CursoDAO {

	// CREATE
	public Curso inserir(Curso curso) throws CursoException {
		
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = (PreparedStatement) conn.prepareStatement(
					"INSERT INTO tb_curso " + "(nm_curso)" + "VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, curso.getNmCurso());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

			if (rowsAffected > 0) {

				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					curso.setIdCurso(id);
				}
			}
		} catch (SQLException e) {
			throw new CursoException(e.getMessage());
		}

		return curso;
	}

	// READ
	public List<Curso> buscaTodos() throws CursoException {
		ResultSet rs = null;
		PreparedStatement st = null;
		List<Curso> alunos = new ArrayList<Curso>();
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id_curso, nm_curso" + " FROM tb_curso "
					+ " ORDER BY id_aluno ");

			rs = st.executeQuery();

			while (rs.next()) {
				Curso curso = new Curso();

				curso.setIdCurso(rs.getInt("id_aluno"));
				curso.setNmCurso(rs.getString("nm_curso"));

				alunos.add(curso);
			}

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());

		}

		return alunos;
	}

	public Curso buscaPorId(Integer id) throws CursoException {
		Curso curso = new Curso();

		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			st = conn.prepareStatement("SELECT id_curso, nm_curso" + " FROM tb_aluno "
					+ " WHERE id_curso = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {

				curso.setIdCurso(rs.getInt("id_curso"));
				curso.setNmCurso(rs.getString("nm_curso"));

			}

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());

		}


		return curso;
	}

	// UPDATE

	public Curso alterar(Curso curso, String novoNome) throws CursoException {

		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			
			String query = "UPDATE tb_curso "
					+ " SET "
					+ " nm_curso = ? "
					+ " WHERE id_curso = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, novoNome);
			st.setInt(2, curso.getIdCurso());
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);
			curso.setNmCurso(novoNome);

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());
		}
		
		return curso;
	}

	// DELETE

	public void excluir(Integer id) throws CursoException {
		PreparedStatement st = null;
		try {
			Connection conn = DB.getConnection();
			
			String query = " DELETE FROM tb_curso WHERE id_curso = ? ; ";
			st = (PreparedStatement) conn.prepareStatement(query);
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			System.out.println("Linhas alteradas: " + rowsAffected);

		} catch (SQLException e) {
			throw new CursoException(e.getMessage());
		}
		
	}
}

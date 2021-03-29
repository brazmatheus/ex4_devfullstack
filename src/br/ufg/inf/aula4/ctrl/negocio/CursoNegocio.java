package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.model.dao.CursoDAO;
import br.ufg.inf.aula4.model.entities.Curso;

public class CursoNegocio {


		CursoDAO dao = new CursoDAO();
	
		public Curso inserir(Curso curso) throws CursoException {
			this.validarDisciplina(curso);
			dao.inserir(curso);
			return curso;
		}
		
		// READ
		public List<Curso> buscaTodos() throws CursoException{
			return dao.buscaTodos();	
		}
		
		public Curso buscaPorId(Integer id) throws CursoException {
			
			return dao.buscaPorId(id);
		}
		
		
		// UPDATE
		
		public Curso alterar(Curso curso, String novoNome) throws CursoException {		
			this.validarDisciplina(curso);
			return dao.alterar(curso, novoNome);
		}
		
		// DELETE
		
		public void excluir(Integer id) throws CursoException {
			dao.excluir(id);
		}
		
		private void validarDisciplina(Curso curso) throws CursoException {
			if (curso.getIdCurso() <= 0 || curso.getIdCurso() == null) {
				throw new CursoException("Id curso está 0");
			}

			if (curso.getNmCurso().equals("")) {
				throw new CursoException("Nome da curso é obrigatório.");
			}
		}
}

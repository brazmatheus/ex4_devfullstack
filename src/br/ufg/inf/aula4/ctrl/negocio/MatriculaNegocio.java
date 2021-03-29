package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.model.dao.MatriculaDAO;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaNegocio {


		MatriculaDAO dao = new MatriculaDAO();
	
		public Matricula inserir(Matricula matricula) throws MatriculaException {
			this.validarDisciplina(matricula);
			dao.inserir(matricula);
			return matricula;
		}
		
		// READ
		public List<Matricula> buscaTodos() throws MatriculaException{
			return dao.buscaTodos();	
		}
		
		public Matricula buscaPorId(Integer id) throws MatriculaException {
			
			return dao.buscaPorId(id);
		}
		
		
		// UPDATE
		
//		public Matricula alterar(Matricula disciplina) throws MatriculaException {		
//			this.validarDisciplina(disciplina);
//			return dao.alterar(disciplina);
//		}
		
		// DELETE
		
		public void excluir(Integer id) throws MatriculaException {
			dao.excluir(id);
		}
		
		private void validarDisciplina(Matricula matricula) throws MatriculaException {
			if (matricula.getAluno().getIdAluno() <= 0) {
				throw new MatriculaException("ID aluno menor que 0 ou vazio.");
			}

			if (matricula.getOferta().getIdOferta() == null || matricula.getOferta().getIdOferta() == 0) {
				throw new MatriculaException("ID Oferta menor que 0 ou vazio.");
			}
		}
}

package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.model.dao.AlunoDAO;
import br.ufg.inf.aula4.model.entities.Aluno;

public class AlunoNegocio {


		AlunoDAO dao = new AlunoDAO();
	
		public Aluno inserir(Aluno aluno) throws AlunoException {
			this.validarAluno(aluno);
			dao.inserir(aluno);
			return aluno;
		}
		
		// READ
		public List<Aluno> buscaTodos() throws AlunoException{
			return dao.buscaTodos();	
		}
		
		public Aluno buscaPorId(Integer id) throws AlunoException {
			
			return dao.buscaPorId(id);
		}
		
		
		// UPDATE
		
		public Aluno alterar(Aluno aluno, boolean ativo) throws AlunoException {		
			this.validarAluno(aluno);
			return dao.alterar(aluno, ativo);
		}
		
		// DELETE
		
		public void excluir(Integer id) throws AlunoException {
			dao.excluir(id);
		}
		
		private void validarAluno(Aluno aluno) throws AlunoException {
			if (aluno.getPessoa().getIdPessoa() <= 0){
				throw new AlunoException("Aluno não está vinculado com pessoa");
			}
		}
}

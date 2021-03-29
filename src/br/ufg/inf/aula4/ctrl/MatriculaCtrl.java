package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.negocio.DisciplinaNegocio;
import br.ufg.inf.aula4.ctrl.negocio.MatriculaNegocio;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaCtrl {

	MatriculaNegocio negocio = new MatriculaNegocio();

	public Matricula inserir(Matricula matricula) {
		try {
			matricula = negocio.inserir(matricula);
			System.out.println("Matricula cadastrada com sucesso: " + matricula);
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar cadastrar matricula.");
			System.out.println(e.getMessage());
		}
		return matricula;
	}

	public List<Matricula> buscaTodos() {
		List<Matricula> disciplinas = null;
		try {
			disciplinas = negocio.buscaTodos();
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar as disciplinas cadastradas.");
			System.out.println(e.getMessage());
		}
		return disciplinas;
	}

	public Matricula buscaPorId(Integer id) {
		Matricula matricula = null;
		try {
			matricula = negocio.buscaPorId(id);
		} catch (MatriculaException e) {
			System.out.println("Erro tentar buscar matricula do ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return matricula;
	}

//	public Matricula alterar(Matricula matricula) {
//		try {
//			matricula = negocio.alterar(matricula);
//			System.out.println("Matricula alterada com sucesso: " + matricula);
//		} catch (MatriculaException e) {
//			System.out.println("Erro ao tentar alterar matricula com ID: " + matricula.getIdDisciplina() + ".");
//			System.out.println(e.getMessage());
//		}
//		return matricula;
//	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Matricula excluída com sucesso.");
		} catch (MatriculaException e) {
			System.out.println("Erro ao tentar excluir a matricula");
			System.out.println(e.getMessage());
		}
	}
}

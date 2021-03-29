package br.ufg.inf.aula4.ctrl;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.negocio.CursoNegocio;
import br.ufg.inf.aula4.model.entities.Curso;

public class CursoCtrl {

	CursoNegocio negocio = new CursoNegocio();

	public Curso inserir(Curso curso) {
		try {
			curso = negocio.inserir(curso);
			System.out.println("Curso cadastrada com sucesso: " + curso);
		} catch (CursoException e) {
			System.out.println("Erro ao tentar cadastrar curso.");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public List<Curso> buscaTodos() {
		List<Curso> disciplinas = null;
		try {
			disciplinas = negocio.buscaTodos();
		} catch (CursoException e) {
			System.out.println("Erro tentar buscar as disciplinas cadastradas.");
			System.out.println(e.getMessage());
		}
		return disciplinas;
	}

	public Curso buscaPorId(Integer id) {
		Curso curso = null;
		try {
			curso = negocio.buscaPorId(id);
		} catch (CursoException e) {
			System.out.println("Erro tentar buscar curso do ID: " + id + ".");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public Curso alterar(Curso curso) {
		try {
			curso = negocio.alterar(curso, "Novo Nome");
			System.out.println("Curso alterada com sucesso: " + curso);
		} catch (CursoException e) {
			System.out.println("Erro ao tentar alterar curso com ID: " + curso.getIdCurso() + ".");
			System.out.println(e.getMessage());
		}
		return curso;
	}

	public void excluir(Integer id) {
		try {
			negocio.excluir(id);
			System.out.println("Curso excluída com sucesso.");
		} catch (CursoException e) {
			System.out.println("Erro ao tentar excluir a curso");
			System.out.println(e.getMessage());
		}
	}
}

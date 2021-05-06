package br.ufg.inf.aula4.app;

import br.ufg.inf.aula4.ctrl.AlunoCtrl;
import br.ufg.inf.aula4.ctrl.CursoCtrl;
import br.ufg.inf.aula4.ctrl.DisciplinaCtrl;
import br.ufg.inf.aula4.ctrl.MatriculaCtrl;
import br.ufg.inf.aula4.ctrl.OfertaCtrl;
import br.ufg.inf.aula4.ctrl.PessoaCtrl;
import br.ufg.inf.aula4.ctrl.ProfessorCtrl;

public class aplication {

	public static void main(String[] args) {

		System.out.println("Começando por aqui");

		TesteApp testeApp = new TesteApp();
		
		testeApp.testeCrudDisciplina(new DisciplinaCtrl());
		testeApp.testeCrudPessoa(new PessoaCtrl());
		testeApp.testeCrudCurso(new CursoCtrl());
		testeApp.testeCrudAluno(new AlunoCtrl(), new PessoaCtrl(), new CursoCtrl());
		testeApp.testeCrudProfessor(new ProfessorCtrl(), new PessoaCtrl());
		testeApp.testeCrudOferta(new OfertaCtrl(), new DisciplinaCtrl(), new ProfessorCtrl());
		testeApp.testeCrudMatricula(new MatriculaCtrl(), new AlunoCtrl(), new OfertaCtrl());
	}
	
	

}

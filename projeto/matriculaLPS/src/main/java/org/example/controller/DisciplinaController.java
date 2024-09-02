package org.example.controller;

import org.example.dao.DisciplinaDAO;
import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.model.Professor;

import java.util.List;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;

    public DisciplinaController() {
        this.disciplinaDAO = new DisciplinaDAO();
    }

    public void adicionarAlunoADisciplina(Disciplina disciplina, Aluno aluno) {
        disciplina.addAluno(aluno);
        disciplinaDAO.salvarDisciplina(disciplina);
    }

    public void removerAlunoDaDisciplina(Disciplina disciplina, Aluno aluno) {
        List<Aluno> alunos = disciplina.getAlunos();
        if (alunos.contains(aluno)) {
            alunos.remove(aluno);
            disciplinaDAO.salvarDisciplina(disciplina);
        }
    }

    public boolean verificarSeDisciplinaEstaAtiva(Disciplina disciplina) {
        return disciplina.getAlunos().size() >= disciplina.getMinAlunos();
    }

    public void alterarProfessorDaDisciplina(Disciplina disciplina, Professor professor) {
        disciplina.setProfessor(professor);
        disciplinaDAO.salvarDisciplina(disciplina);
    }

    public List<Aluno> listarAlunosDaDisciplina(Disciplina disciplina) {
        return disciplina.getAlunos();
    }

    public List<Disciplina> carregarDisciplinas() {
        return disciplinaDAO.carregarDisciplinas();
    }

    public void verificarEAtualizarStatusDisciplina(Disciplina disciplina) {
        disciplina.verificarStatus();
        disciplinaDAO.salvarDisciplina(disciplina);
    }
}

package org.example.controller;

import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.model.Professor;

import java.util.List;

public class DisciplinaController {

    public void adicionarAlunoADisciplina(Disciplina disciplina, Aluno aluno) {
        List<Aluno> alunos = disciplina.getAlunos();
        if (alunos.size() < disciplina.getMaxAlunos() && !alunos.contains(aluno)) {
            alunos.add(aluno);
        }
    }

    public void removerAlunoDaDisciplina(Disciplina disciplina, Aluno aluno) {
        List<Aluno> alunos = disciplina.getAlunos();
        if (alunos.contains(aluno)) {
            alunos.remove(aluno);
        }
    }

    public boolean verificarSeDisciplinaEstaAtiva(Disciplina disciplina) {
        return disciplina.getAlunos().size() >= disciplina.getMinAlunos();
    }

    public void alterarProfessorDaDisciplina(Disciplina disciplina, Professor professor) {
        disciplina.setProfessor(professor);
    }

    public List<Aluno> listarAlunosDaDisciplina(Disciplina disciplina) {
        return disciplina.getAlunos();
    }
}

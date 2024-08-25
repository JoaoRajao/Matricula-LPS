package org.example.controller;

import org.example.dao.SecretariaDAO;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.Professor;
import org.example.model.Secretaria;

import java.util.List;

public class SecretariaController {
    private SecretariaDAO secretariaDAO;

    public SecretariaController() {
        this.secretariaDAO = new SecretariaDAO();
    }



    public void adicionarCurso(Secretaria secretaria, Curso curso) {
        List<Curso> cursos = secretaria.getCursos();
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            secretariaDAO.salvarSecretaria(secretaria);
        }
    }

    public void adicionarProfessor(Secretaria secretaria, Professor professor) {
        List<Professor> professores = secretaria.getProfessores();
        if (!professores.contains(professor)) {
            professores.add(professor);
            secretariaDAO.salvarSecretaria(secretaria);
        }
    }

    public void adicionarDisciplina(Secretaria secretaria, Disciplina disciplina) {
        List<Disciplina> disciplinas = secretaria.getDisciplinas();
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            secretariaDAO.salvarSecretaria(secretaria);
        }
    }

    public static void adicionarDisciplinaAoProfessor(Disciplina disciplina, Professor professor) {
        if (!professor.getDisciplinas().contains(disciplina)) {
            professor.getDisciplinas().add(disciplina);
        }
    }

    public void adicionarAluno(Secretaria secretaria, Aluno aluno) {
        List<Aluno> alunos = secretaria.getAlunos();
        if (!alunos.contains(aluno)) {
            alunos.add(aluno);
            secretariaDAO.salvarSecretaria(secretaria);
        }
    }

    public List<Curso> listarCursos(Secretaria secretaria) {
        return secretaria.getCursos();
    }

    public List<Professor> listarProfessores(Secretaria secretaria) {
        return secretaria.getProfessores();
    }

    public List<Disciplina> listarDisciplinas(Secretaria secretaria) {
        return secretaria.getDisciplinas();
    }

    public List<Aluno> listarAlunos(Secretaria secretaria) {
        return secretaria.getAlunos();
    }
}

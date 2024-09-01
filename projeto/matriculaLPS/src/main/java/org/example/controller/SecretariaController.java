package org.example.controller;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.dao.DisciplinaDAO;
import org.example.dao.ProfessorDAO;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.Professor;
import org.example.model.Secretaria;

import java.util.List;

public class SecretariaController {
    private AlunoDAO alunoDAO;
    private CursoDAO cursoDAO;
    private DisciplinaDAO disciplinaDAO;
    private ProfessorDAO professorDAO;

    public SecretariaController() {
        this.alunoDAO = new AlunoDAO();
        this.cursoDAO = new CursoDAO();
        this.disciplinaDAO = new DisciplinaDAO();
        this.professorDAO = new ProfessorDAO();
    }

    public void adicionarCurso(Curso curso) {
        cursoDAO.salvarCurso(curso);
    }

    public void adicionarProfessor(Professor professor) {
        List<Professor> professores = professorDAO.carregarProfessores();
        if (!professores.contains(professor)) {
            professorDAO.salvarProfessor(professor);
        }
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinaDAO.salvarDisciplina(disciplina);
    }

    public void adicionarAluno(Aluno aluno) {
        List<Aluno> alunos = alunoDAO.carregarAlunos();
        if (!alunos.contains(aluno)) {
            alunoDAO.salvarAluno(aluno);
        }
    }

    public static void adicionarDisciplinaAoProfessor(Disciplina disciplina, Professor professor) {
        if (!professor.getDisciplinas().contains(disciplina)) {
            professor.getDisciplinas().add(disciplina);
        }
    }

    public List<Curso> listarCursos() {
        return cursoDAO.carregarCursos();
    }

    public List<Professor> listarProfessores() {
        return professorDAO.carregarProfessores();
    }

    public  List<Disciplina> listarDisciplinas() {
        return disciplinaDAO.carregarDisciplinas();
    }

    public List<Aluno> listarAlunos() {
        return alunoDAO.carregarAlunos();
    }

    public boolean verificarStatusDisciplina(Disciplina disciplina) {
        int numAlunosInscritos = disciplina.getAlunos().size();

        if (numAlunosInscritos < disciplina.getMinAlunos()) {
            cancelarDisciplina(disciplina);
            return false;
        } else if (numAlunosInscritos <= disciplina.getMaxAlunos()) {
            disciplina.setInscricoesEncerradas(true);
            return true;
        } else {
            disciplina.setAtiva(true);
            return true;
        }
    }

    private void cancelarDisciplina(Disciplina disciplina) {
        disciplina.setAtiva(false);
    }
}

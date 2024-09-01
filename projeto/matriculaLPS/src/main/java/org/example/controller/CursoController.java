package org.example.controller;

import org.example.dao.CursoDAO;
import org.example.model.Curso;
import org.example.model.Disciplina;

import java.util.List;

public class CursoController {

    private CursoDAO cursoDAO;

    public CursoController() {
        this.cursoDAO = new CursoDAO();
    }

    public void adicionarDisciplinaAoCurso(Curso curso, Disciplina disciplina) {
        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            cursoDAO.salvarCurso(curso);
        }
    }

    public void removerDisciplinaDoCurso(Curso curso, Disciplina disciplina) {
        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
            cursoDAO.salvarCurso(curso);
        }
    }

    public List<Disciplina> listarDisciplinasDoCurso(Curso curso) {
        return curso.getDisciplinas();
    }

    public List<Curso> carregarCursos() {
        return cursoDAO.carregarCursos();
    }
}

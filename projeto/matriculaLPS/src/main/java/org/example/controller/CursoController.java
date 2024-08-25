package org.example.controller;

import org.example.model.Curso;
import org.example.model.Disciplina;

import java.util.List;

public class CursoController {

    public void adicionarDisciplinaAoCurso(Curso curso, Disciplina disciplina) {
        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removerDisciplinaDoCurso(Curso curso, Disciplina disciplina) {
        List<Disciplina> disciplinas = curso.getDisciplinas();
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
        }
    }

    public List<Disciplina> listarDisciplinasDoCurso(Curso curso) {
        return curso.getDisciplinas();
    }
}

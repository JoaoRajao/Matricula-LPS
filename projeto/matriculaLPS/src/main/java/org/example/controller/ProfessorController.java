package org.example.controller;

import org.example.dao.ProfessorDAO;
import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.model.Professor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfessorController {

    private ProfessorDAO professorDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
    }

    public void adicionarProfessor(Professor professor) {
        professorDAO.salvarProfessor(professor);
    }

    public List<Professor> carregarProfessores() {
        return professorDAO.carregarProfessores();
    }

    public List<String> visualizarDisciplinasLecionadas(Professor professor) {
        return professor.getDisciplinas().stream()
                .map(Disciplina::getNome)
                .collect(Collectors.toList());
    }

    public Map<String, List<Aluno>> visualizarAlunosPorDisciplina(Professor professor) {
        return professor.getDisciplinas().stream()
                .collect(Collectors.toMap(
                        Disciplina::getNome,
                        Disciplina::getAlunos
                ));
    }
}

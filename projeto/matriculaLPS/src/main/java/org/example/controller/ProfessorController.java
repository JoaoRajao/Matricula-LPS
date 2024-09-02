
package org.example.controller;

import org.example.dao.*;

import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.model.Professor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProfessorController {

    private ProfessorDAO professorDAO;
    private DisciplinaDAO disciplinaDAO;
    private AlunoDAO alunoDAO;

    public ProfessorController() {
        this.professorDAO = new ProfessorDAO();
        this.disciplinaDAO = new DisciplinaDAO();
        this.alunoDAO = new AlunoDAO();
    }

    public void adicionarProfessor(Professor professor) {
        professorDAO.salvarProfessor(professor);
    }

    public List<Professor> carregarProfessores() {
        return professorDAO.carregarProfessores();
    }

    public List<String> visualizarDisciplinasLecionadas(Professor professor) {
        List<Disciplina> todasDisciplinas = disciplinaDAO.carregarDisciplinas();

        return todasDisciplinas.stream()
                .filter(disciplina -> disciplina.getProfessor() != null
                        && disciplina.getProfessor().getNome().equals(professor.getNome()))
                .map(Disciplina::getNome)
                .collect(Collectors.toList());
    }

    public Map<String, List<Aluno>> visualizarAlunosPorDisciplina(Professor professor) {

        List<Disciplina> todasDisciplinas = disciplinaDAO.carregarDisciplinas();
        List<Aluno> todosAlunos = alunoDAO.carregarAlunos();


        List<Disciplina> disciplinasLecionadas = todasDisciplinas.stream()
                .filter(disciplina -> disciplina.getProfessor() != null
                        && disciplina.getProfessor().getNome().equals(professor.getNome()))
                .collect(Collectors.toList());


        Map<String, List<Aluno>> alunosPorDisciplina = new HashMap<>();

        for (Disciplina disciplina : disciplinasLecionadas) {
            List<Aluno> alunosDaDisciplina = todosAlunos.stream()
                    .filter(aluno -> aluno.getDisciplinasMatriculadas().stream()
                            .anyMatch(d -> d.getNome().equals(disciplina.getNome())))
                    .collect(Collectors.toList());


            alunosPorDisciplina.merge(disciplina.getNome(), alunosDaDisciplina, (existing, newAlunos) -> {
                existing.addAll(newAlunos);
                return existing;
            });
        }

        return alunosPorDisciplina;
    }
}

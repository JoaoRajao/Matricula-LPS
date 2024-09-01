package org.example.controller;

import org.example.dao.AlunoDAO;
import org.example.dao.DisciplinaDAO;
import org.example.model.Aluno;
import org.example.model.Disciplina;
import org.example.model.TipoDisciplina;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoController {
    private AlunoDAO alunoDAO;
    private DisciplinaController disciplinaController;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.disciplinaController = new DisciplinaController();
    }

    public void adicionarAluno(Aluno aluno) {
        alunoDAO.salvarAluno(aluno);
    }

    public List<Aluno> carregarAlunos() {
        return alunoDAO.carregarAlunos();
    }

    public void salvarAluno(Aluno aluno) {
        alunoDAO.salvarAluno(aluno);
    }

    public List<Disciplina> carregarTodasDisciplinas() {
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        return disciplinaDAO.carregarDisciplinas();
    }


    public void matricularDisciplinas(Aluno aluno, List<Disciplina> disciplinas) {
        int totalDisciplinasExistentes = aluno.getDisciplinasMatriculadas().size();
        int totalObrigatoriasExistentes = aluno.getDisciplinasObrigatorias().size();
        int totalOptativasExistentes = aluno.getDisciplinasOptativas().size();

        for (Disciplina disciplina : disciplinas) {
            if (totalDisciplinasExistentes >= 6) {
                System.out.println("Não é possível matricular em mais de 6 disciplinas no total.");
                break;
            }

            if (disciplina.getTipo() == TipoDisciplina.OBRIGATORIA) {
                if (totalObrigatoriasExistentes >= 4) {
                    System.out.println("Não é possível matricular em mais de 4 disciplinas obrigatórias.");
                    continue;
                }
                aluno.getDisciplinasObrigatorias().add(disciplina);
                totalObrigatoriasExistentes++;
            } else if (disciplina.getTipo() == TipoDisciplina.OPTATIVA) {
                if (totalOptativasExistentes >= 2) {
                    System.out.println("Não é possível matricular em mais de 2 disciplinas optativas.");
                    continue;
                }
                aluno.getDisciplinasOptativas().add(disciplina);
                totalOptativasExistentes++;
            }
            totalDisciplinasExistentes++;
        }
        alunoDAO.salvarAluno(aluno);
    }

    public void cancelarMatricula(Aluno aluno, Disciplina disciplina) {
        boolean removed = false;

        if (aluno.getDisciplinasObrigatorias().contains(disciplina)) {
            aluno.getDisciplinasObrigatorias().remove(disciplina);
            removed = true;
        } else if (aluno.getDisciplinasOptativas().contains(disciplina)) {
            aluno.getDisciplinasOptativas().remove(disciplina);
            removed = true;
        }

        if (removed) {
            disciplinaController.removerAlunoDaDisciplina(disciplina, aluno);
            alunoDAO.salvarAluno(aluno);
        }
    }

    public List<String> visualizarDisciplinasMatriculadas(Aluno aluno) {
        return aluno.getDisciplinasMatriculadas().stream()
                .map(Disciplina::getNome)
                .collect(Collectors.toList());
    }
}

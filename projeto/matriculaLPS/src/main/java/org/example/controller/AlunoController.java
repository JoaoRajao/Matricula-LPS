package org.example.controller;

import org.example.dao.*;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Disciplina;

import java.util.List;
import java.util.stream.Collectors;

public class AlunoController {
    private AlunoDAO alunoDAO;
    private DisciplinaController disciplinaController;

    public AlunoController() {
        this.alunoDAO = new AlunoDAO();
        this.disciplinaController = new DisciplinaController(); // Inicialize a variável aqui
    }
    public void adicionarAluno(Aluno aluno) {
        alunoDAO.salvarAluno(aluno);
    }

    public List<Aluno> carregarAlunos(List<Curso> cursosDisponiveis) {
        return alunoDAO.carregarAlunos(cursosDisponiveis);
    }

    public void matricularDisciplina(Aluno aluno, Disciplina disciplina) {
        List<Disciplina> disciplinasMatriculadas = aluno.getDisciplinasMatriculadas();
        if (!disciplinasMatriculadas.contains(disciplina)) {
            disciplinaController.adicionarAlunoADisciplina(disciplina, aluno); // Adiciona o aluno à disciplina
            disciplinasMatriculadas.add(disciplina); // Adiciona a disciplina ao aluno
            alunoDAO.salvarAluno(aluno); // Atualiza o arquivo após a matrícula
        }
    }

    public void cancelarMatricula(Aluno aluno, Disciplina disciplina) {
        List<Disciplina> disciplinasMatriculadas = aluno.getDisciplinasMatriculadas();
        if (disciplinasMatriculadas.contains(disciplina)) {
            disciplinaController.removerAlunoDaDisciplina(disciplina, aluno); // Remove o aluno da disciplina
            disciplinasMatriculadas.remove(disciplina); // Remove a disciplina do aluno
            alunoDAO.salvarAluno(aluno); // Atualiza o arquivo após o cancelamento
        }
    }

    public List<String> visualizarDisciplinasMatriculadas(Aluno aluno) {
        return aluno.getDisciplinasMatriculadas().stream()
                .map(Disciplina::getNome)
                .collect(Collectors.toList());
    }
}

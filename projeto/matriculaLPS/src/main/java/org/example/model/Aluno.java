package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome, String matricula, Curso curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinasMatriculadas = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public void matricularDisciplina(Disciplina disciplina) {
        if (!disciplinasMatriculadas.contains(disciplina) && disciplina.adicionarAluno(this)) {
            disciplinasMatriculadas.add(disciplina);
            System.out.println("Aluno matriculado na disciplina: " + disciplina.getNome());
        } else {
            System.out.println("Não foi possível matricular o aluno na disciplina: " + disciplina.getNome());
        }
    }

    public void cancelarMatricula(Disciplina disciplina) {
        if (disciplinasMatriculadas.contains(disciplina)) {
            disciplinasMatriculadas.remove(disciplina);
            disciplina.removerAluno(this);
            System.out.println("Matrícula cancelada na disciplina: " + disciplina.getNome());
        } else {
            System.out.println("Aluno não está matriculado na disciplina: " + disciplina.getNome());
        }
    }

    public List<String> visualizarDisciplinasMatriculadas() {
        List<String> nomesDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : disciplinasMatriculadas) {
            nomesDisciplinas.add(disciplina.getNome());
        }
        return nomesDisciplinas;
    }

    @Override
    public String toString() {
        return nome + ";" + matricula + ";" + curso.getNome();
    }}
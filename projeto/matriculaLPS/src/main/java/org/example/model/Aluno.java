package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;
    private List<Disciplina> disciplinas;

    public Aluno(String nome, String matricula, Curso curso) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.disciplinas = new ArrayList<>();
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void matricularDisciplina(Disciplina disciplina) {
        if (disciplina.adicionarAluno(this)) {
            disciplinas.add(disciplina);
            System.out.println("Aluno matriculado na disciplina: " + disciplina.getNome());
        } else {
            System.out.println("Não foi possível matricular o aluno na disciplina: " + disciplina.getNome());
        }
    }

    public void cancelarMatricula(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
            disciplina.removerAluno(this);
            System.out.println("Matrícula cancelada na disciplina: " + disciplina.getNome());
        } else {
            System.out.println("Aluno não está matriculado na disciplina: " + disciplina.getNome());
        }
    }
    @Override
    public String toString() {
        return nome + ";" + matricula + ";" + curso.getNome();
    }
}

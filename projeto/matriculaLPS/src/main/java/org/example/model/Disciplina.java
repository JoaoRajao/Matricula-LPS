package org.example.model;



import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private Professor professor;
    private List<Aluno> alunos;
    private final int maxAlunos = 60;
    private final int minAlunos = 3;

    public Disciplina(String nome, int creditos, Professor professor) {
        this.nome = nome;
        this.creditos = creditos;
        this.professor = professor;
        this.alunos = new ArrayList<>();
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (alunos.size() < maxAlunos) {
            alunos.add(aluno);
            return true;
        }
        return false;
    }

    public boolean removerAluno(Aluno aluno) {
        return alunos.remove(aluno);
    }

    public boolean isAtiva() {
        return alunos.size() >= minAlunos;
    }

    @Override
    public String toString() {
        return nome + ";" + creditos + ";" + professor.getId();
    }
}

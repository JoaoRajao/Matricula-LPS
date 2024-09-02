package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private int creditos;
    private Professor professor;
    private List<Aluno> alunos;
    private TipoDisciplina tipo;
    private final int maxAlunos = 60;
    private final int minAlunos = 3;
    private boolean ativa;
    private boolean inscricoesEncerradas;

    public Disciplina(String nome, int creditos, Professor professor, TipoDisciplina tipo) {
        this.nome = nome;
        this.creditos = creditos;
        this.professor = professor;
        this.alunos = new ArrayList<>();
        this.tipo = tipo;
        this.ativa = false;
        this.inscricoesEncerradas = false;
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

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public int getMaxAlunos() {
        return maxAlunos;
    }

    public int getMinAlunos() {
        return minAlunos;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isInscricoesEncerradas() {
        return inscricoesEncerradas;
    }

    public void setInscricoesEncerradas(boolean inscricoesEncerradas) {
        this.inscricoesEncerradas = inscricoesEncerradas;
    }

    public void addAluno(Aluno aluno) {
        if (!inscricoesEncerradas && alunos.size() < maxAlunos) {
            alunos.add(aluno);
            if (alunos.size() >= maxAlunos) {
                inscricoesEncerradas = true;
            }
        } else {
            System.out.println("Não é possível adicionar mais alunos. Inscrições encerradas ou número máximo de alunos atingido.");
        }
    }

    public void verificarStatus() {
        if (alunos.size() >= minAlunos) {
            ativa = true;
        } else {
            ativa = false;
            System.out.println("Disciplina " + nome + " cancelada por falta de alunos.");
        }
    }

    @Override
    public String toString() {
        return nome + ";" + creditos + ";" + (professor != null ? professor.getNome() : "Sem Professor") + ";" + tipo;
    }
}

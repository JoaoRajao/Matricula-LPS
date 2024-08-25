package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;
    private List<Disciplina> disciplinasMatriculadas;
    private String login;
    private String senha;

    public Aluno(String nome, String matricula, Curso curso, String login, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.login = login;
        this.senha = senha;
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

    public void setDisciplinasMatriculadas(List<Disciplina> disciplinasMatriculadas) {
        this.disciplinasMatriculadas = disciplinasMatriculadas;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aluno) {
            Aluno aluno = (Aluno) obj;
            return matricula.toLowerCase().equals(aluno.getMatricula().toLowerCase());
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return nome + ";" + matricula + ";" + curso.getNome() + ";" + login + ";" + senha;
    }
}

package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Secretaria {
    private List<Curso> cursos;
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private String login;
    private String senha;

    public Secretaria(String login, String senha) {
        this.cursos = new ArrayList<>();
        this.disciplinas = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.login = login;
        this.senha = senha;
    }


    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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
    public String toString() {
        return login + ";" + senha;
    }
}

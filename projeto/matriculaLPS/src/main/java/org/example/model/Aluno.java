package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String nome;
    private String matricula;
    private Curso curso;
    private List<Disciplina> disciplinasObrigatorias;
    private List<Disciplina> disciplinasOptativas;
    private String login;
    private String senha;

    public Aluno(String nome, String matricula, Curso curso, String login, String senha) {
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.login = login;
        this.senha = senha;
        this.disciplinasObrigatorias = new ArrayList<>();
        this.disciplinasOptativas = new ArrayList<>();
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

    public List<Disciplina> getDisciplinasObrigatorias() {
        return disciplinasObrigatorias;
    }

    public void setDisciplinasObrigatorias(List<Disciplina> disciplinasObrigatorias) {
        this.disciplinasObrigatorias = disciplinasObrigatorias;
    }

    public List<Disciplina> getDisciplinasOptativas() {
        return disciplinasOptativas;
    }

    public void setDisciplinasOptativas(List<Disciplina> disciplinasOptativas) {
        this.disciplinasOptativas = disciplinasOptativas;
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        List<Disciplina> todasDisciplinas = new ArrayList<>(disciplinasObrigatorias);
        todasDisciplinas.addAll(disciplinasOptativas);
        return todasDisciplinas;
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
        StringBuilder sb = new StringBuilder();
        sb.append(nome).append(";").append(matricula).append(";").append(curso.getNome()).append(";")
                .append(login).append(";").append(senha).append(";");

        for (Disciplina d : disciplinasObrigatorias) {
            sb.append(d.getNome()).append("(O),");
        }

        for (Disciplina d : disciplinasOptativas) {
            sb.append(d.getNome()).append("(P),");
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}

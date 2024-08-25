package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private String id;
    private List<Disciplina> disciplinas;
    private String login;
    private String senha;

    public Professor(String nome, String id, String login, String senha) {
        this.nome = nome;
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.disciplinas = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
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
        if (obj instanceof Professor) {
            Professor professor = (Professor) obj;
            return id.toLowerCase().equals(professor.getId().toLowerCase());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return nome + ";" + id + ";" + login + ";" + senha;
    }
}

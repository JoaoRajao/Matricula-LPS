package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private String id;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String id) {
        this.nome = nome;
        this.id = id;
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

    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
            System.out.println("Disciplina adicionada ao professor: " + disciplina.getNome());
        } else {
            System.out.println("O professor já está associado a essa disciplina: " + disciplina.getNome());
        }
    }

    @Override
    public String toString() {
        return nome + ";" + id;
    }
}

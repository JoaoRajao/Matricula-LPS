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

    // Getters e Setters
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

    public List<String> visualizarDisciplinasLecionadas() {
        List<String> nomesDisciplinas = new ArrayList<>();
        for (Disciplina disciplina : disciplinas) {
            nomesDisciplinas.add(disciplina.getNome());
        }
        return nomesDisciplinas;
    }

    @Override
    public String toString() {
        return nome + ";" + id;
    }
}

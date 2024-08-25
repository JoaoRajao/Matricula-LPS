package org.example.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SecretariaTest {

    private Secretaria secretaria;
    private Curso curso;
    private Disciplina disciplina;
    private Professor professor;
    private Aluno aluno;

    @Before
    public void setUp() {
        secretaria = new Secretaria();
        curso = new Curso("Engenharia de Software", 240);
        disciplina = new Disciplina("Algoritmos", 4, new Professor("Dr. Pedro", "P01"));
        professor = new Professor("Dra. Ana", "P02");
        aluno = new Aluno("João Silva", "20220001", curso);
    }

    @Test
    public void testAdicionarCurso() {
        secretaria.adicionarCurso(curso);
        assertTrue(secretaria.getCursos().contains(curso));
    }

    @Test
    public void testRemoverCurso() {
        secretaria.adicionarCurso(curso);
        secretaria.removerCurso(curso);
        assertFalse(secretaria.getCursos().contains(curso));
    }

    @Test
    public void testAdicionarDisciplina() {
        secretaria.adicionarDisciplina(disciplina);
        assertTrue(secretaria.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testRemoverDisciplina() {
        secretaria.adicionarDisciplina(disciplina);
        secretaria.removerDisciplina(disciplina);
        assertFalse(secretaria.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testAdicionarProfessor() {
        secretaria.adicionarProfessor(professor);
        assertTrue(secretaria.getProfessores().contains(professor));
    }

    @Test
    public void testRemoverProfessor() {
        secretaria.adicionarProfessor(professor);
        secretaria.removerProfessor(professor);
        assertFalse(secretaria.getProfessores().contains(professor));
    }

    @Test
    public void testAdicionarAluno() {
        secretaria.adicionarAluno(aluno);
        assertTrue(secretaria.getAlunos().contains(aluno));
    }

    @Test
    public void testRemoverAluno() {
        secretaria.adicionarAluno(aluno);
        secretaria.removerAluno(aluno);
        assertFalse(secretaria.getAlunos().contains(aluno));
    }

    @Test
    public void testAdicionarDisciplinaAoProfessor() {
        secretaria.adicionarProfessor(professor);
        secretaria.adicionarDisciplinaAoProfessor(disciplina, professor);
        assertTrue(professor.getDisciplinas().contains(disciplina));
    }
}

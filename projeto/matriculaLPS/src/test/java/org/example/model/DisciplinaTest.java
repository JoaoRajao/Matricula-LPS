package org.example.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DisciplinaTest {

    private Disciplina disciplina;
    private Aluno aluno;

    @Before
    public void setUp() {
        Professor professor = new Professor("Dr. Pedro", "P01");
        disciplina = new Disciplina("Algoritmos", 4, professor);
        aluno = new Aluno("João Silva", "20220001", new Curso("Engenharia de Software", 240));
    }

    @Test
    public void testAdicionarAluno() {
        boolean result = disciplina.adicionarAluno(aluno);
        assertTrue(result);
        assertTrue(disciplina.getAlunos().contains(aluno));
    }

    @Test
    public void testRemoverAluno() {
        disciplina.adicionarAluno(aluno);
        boolean result = disciplina.removerAluno(aluno);
        assertTrue(result);
        assertFalse(disciplina.getAlunos().contains(aluno));
    }

    @Test
    public void testIsAtiva() {
        assertFalse(disciplina.isAtiva());
        disciplina.adicionarAluno(aluno);
        disciplina.adicionarAluno(new Aluno("Maria Oliveira", "20220002", new Curso("Engenharia de Software", 240)));
        disciplina.adicionarAluno(new Aluno("Carlos Souza", "20220003", new Curso("Engenharia de Software", 240)));
        assertTrue(disciplina.isAtiva());
    }

    @Test
    public void testToString() {
        String expected = "Algoritmos;4;P01";
        assertEquals(expected, disciplina.toString());
    }
}

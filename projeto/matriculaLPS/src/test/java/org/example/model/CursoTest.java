package org.example.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CursoTest {

    private Curso curso;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        curso = new Curso("Engenharia de Software", 240);
        disciplina = new Disciplina("Estrutura de Dados", 4, new Professor("Dr. Ana", "P02"));
    }

    @Test
    public void testAdicionarDisciplina() {
        curso.adicionarDisciplina(disciplina);
        assertTrue(curso.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testToString() {
        String expected = "Engenharia de Software;240";
        assertEquals(expected, curso.toString());
    }
}

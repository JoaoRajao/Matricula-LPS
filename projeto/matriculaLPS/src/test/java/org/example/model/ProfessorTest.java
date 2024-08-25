package org.example.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ProfessorTest {

    private Professor professor;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        professor = new Professor("Dr. Pedro", "P01");
        disciplina = new Disciplina("Algoritmos", 4, professor);
    }

    @Test
    public void testVisualizarDisciplinasLecionadas() {
        professor.getDisciplinas().add(disciplina);
        List<String> disciplinas = professor.visualizarDisciplinasLecionadas();
        assertTrue(disciplinas.contains("Algoritmos"));
    }

    @Test
    public void testToString() {
        String expected = "Dr. Pedro;P01";
        assertEquals(expected, professor.toString());
    }
}

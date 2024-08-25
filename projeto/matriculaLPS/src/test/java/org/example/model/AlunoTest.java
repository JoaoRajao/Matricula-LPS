package org.example.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class AlunoTest {

    private Aluno aluno;
    private Curso curso;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        curso = new Curso("Engenharia de Software", 240);
        aluno = new Aluno("João Silva", "20220001", curso);
        disciplina = new Disciplina("Algoritmos", 4, new Professor("Dr. Pedro", "P01"));
    }

    @Test
    public void testMatricularDisciplina() {
        aluno.matricularDisciplina(disciplina);
        List<Disciplina> disciplinas = aluno.getDisciplinasMatriculadas();
        assertTrue(disciplinas.contains(disciplina));
    }

    @Test
    public void testCancelarMatricula() {
        aluno.matricularDisciplina(disciplina);
        aluno.cancelarMatricula(disciplina);
        List<Disciplina> disciplinas = aluno.getDisciplinasMatriculadas();
        assertFalse(disciplinas.contains(disciplina));
    }

    @Test
    public void testVisualizarDisciplinasMatriculadas() {
        aluno.matricularDisciplina(disciplina);
        List<String> disciplinas = aluno.visualizarDisciplinasMatriculadas();
        assertTrue(disciplinas.contains("Algoritmos"));
    }

    @Test
    public void testToString() {
        String expected = "João Silva;20220001;Engenharia de Software";
        assertEquals(expected, aluno.toString());
    }
}

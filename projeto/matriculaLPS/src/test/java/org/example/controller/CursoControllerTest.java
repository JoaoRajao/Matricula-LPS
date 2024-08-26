package org.example.controller;

import org.example.model.Curso;
import org.example.model.Disciplina;
import org.example.model.TipoDisciplina;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CursoControllerTest {

    private CursoController cursoController;
    private Curso curso;
    private Disciplina disciplina;

    @Before
    public void setUp() {
        cursoController = new CursoController();
        curso = new Curso("Engenharia de Software", 240);
        disciplina = new Disciplina("Algoritmos", 4, null, TipoDisciplina.OBRIGATORIA);

    }

    @Test
    public void testAdicionarDisciplinaAoCurso() {
        cursoController.adicionarDisciplinaAoCurso(curso, disciplina);
        assertTrue(curso.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testRemoverDisciplinaDoCurso() {
        cursoController.adicionarDisciplinaAoCurso(curso, disciplina);
        cursoController.removerDisciplinaDoCurso(curso, disciplina);
        assertFalse(curso.getDisciplinas().contains(disciplina));
    }

    @Test
    public void testListarDisciplinasDoCurso() {
        cursoController.adicionarDisciplinaAoCurso(curso, disciplina);
        List<Disciplina> disciplinas = cursoController.listarDisciplinasDoCurso(curso);
        assertEquals(1, disciplinas.size());
        assertEquals(disciplina, disciplinas.get(0));
    }
}
